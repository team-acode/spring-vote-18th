package com.sniff.springvote18th.user.service;

import com.sniff.springvote18th.config.JwtTokenProvider;
import com.sniff.springvote18th.config.redis.RedisService;
import com.sniff.springvote18th.domain.Part;
import com.sniff.springvote18th.domain.Role;
import com.sniff.springvote18th.domain.TeamName;
import com.sniff.springvote18th.domain.User;
import com.sniff.springvote18th.exception.CustomException;
import com.sniff.springvote18th.exception.ErrorCode;
import com.sniff.springvote18th.repository.UserRepository;
import com.sniff.springvote18th.user.dto.request.EmailDto;
import com.sniff.springvote18th.user.dto.request.LoginDto;
import com.sniff.springvote18th.user.dto.request.LoginIdDto;
import com.sniff.springvote18th.user.dto.request.SignUpDto;
import com.sniff.springvote18th.user.dto.response.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    public ResponseEntity<Void> checkLoginId(LoginIdDto loginIdDto) {
        userRepository.findByLoginId(loginIdDto.getLoginId())
                .ifPresent(user -> {
                    throw new CustomException(ErrorCode.LOGIN_ID_DUPLICATED, "이미 존재하는 ID");
                });

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> checkEmail(EmailDto emailDto) {
        userRepository.findByEmail(emailDto.getEmail())
                .ifPresent(user -> {
                    throw new CustomException(ErrorCode.EMAIL_DUPLICATED, "이미 존재하는 email");
                });

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<Void> signUp(SignUpDto signUpDto) {
        userRepository.save(User.builder()
                .loginId(signUpDto.getLoginId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .email(signUpDto.getEmail())
                .name(signUpDto.getName())
                .part(Part.valueOf(signUpDto.getPart()))
                .teamName(TeamName.valueOf(signUpDto.getTeamName()))
                .role(Role.USER)
                .build());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public LoginResponseDto login(LoginDto loginDto) {
        //loginId 확인
        User user = userRepository.findByLoginId(loginDto.getLoginId())
                .orElseThrow(() -> new CustomException(ErrorCode.ID_NOT_FOUND, "존재하지 않는 ID"));

        //비밀번호 확인
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD, "잘못된 비밀번호");
        }

        //토큰 발행
        String accessToken = jwtTokenProvider.createAccessToken(loginDto.getLoginId(), Role.USER.name());

        return new LoginResponseDto(user, accessToken);
    }

    public ResponseEntity<Void> logout(String token) {
        // access token의 유효시간 가져와서 블랙리스트 등록
        Long expirationTime = jwtTokenProvider.getExpiration(token);
        redisService.setBlackList(token, "logout", expirationTime / 1000);
        return ResponseEntity.ok().build();
    }
}
