package com.sniff.springvote18th.partleader;

import com.sniff.springvote18th.domain.Candidate;
import com.sniff.springvote18th.domain.CandidateVote;
import com.sniff.springvote18th.domain.Part;
import com.sniff.springvote18th.domain.User;
import com.sniff.springvote18th.exception.CustomException;
import com.sniff.springvote18th.exception.ErrorCode;
import com.sniff.springvote18th.partleader.dto.CandidateDto;
import com.sniff.springvote18th.partleader.dto.CandidateResponseDto;
import com.sniff.springvote18th.partleader.dto.PartLeaderRequestDto;
import com.sniff.springvote18th.repository.CandidateRepository;
import com.sniff.springvote18th.repository.CandidateVoteRepository;
import com.sniff.springvote18th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartLeaderService {
    private final CandidateRepository candidateRepository;
    private final CandidateVoteRepository candidateVoteRepository;
    private final UserRepository userRepository;

    public CandidateResponseDto getCandidateResult(String part){
        List<Candidate> candidates = candidateRepository.getCandidateList(Part.valueOf(part));
        List<CandidateDto> candidateDtos = candidates.stream().map(CandidateDto::new).toList();
        return new CandidateResponseDto(candidateDtos);
    }

    public CandidateResponseDto getCandidateResultDesc(String part){
        List<Candidate> candidates = candidateRepository.getCandidateListDesc(Part.valueOf(part));
        List<CandidateDto> candidateDtos = candidates.stream().map(CandidateDto::new).toList();
        return new CandidateResponseDto(candidateDtos);
    }

    @Transactional
    public ResponseEntity votePartLeader(String part, PartLeaderRequestDto dto, User user){
        if(!user.isCandidateVoted()){

            if(!user.getPart().name().equals(part)) throw new CustomException(ErrorCode.INVALID_VOTE, "해당 파트에는 투표 불가");

            if (part.equals("FE")) {
                if (!(10 < dto.getId() && dto.getId() <= 20)) {
                    throw new CustomException(ErrorCode.INVALID_VOTE, "올바르지 않은 id");
                }
            } else if (part.equals("BE")) {
                if (!(0 < dto.getId() && dto.getId() <= 10)) {
                    throw new CustomException(ErrorCode.INVALID_VOTE, "올바르지 않은 id");
                }
            }


            candidateRepository.updateVoteCount((long)dto.getId());
            userRepository.updateIsCandidateVoted(user.getId());

            Candidate candidate = candidateRepository.findById((long) dto.getId())
                    .orElseThrow(() -> new CustomException(ErrorCode.CANDIDATE_NOT_FOUND, "후보자 정보 없음"));
            candidateVoteRepository.save(CandidateVote.builder()
                    .user(user)
                    .candidate(candidate)
                    .build());

        } else {
            throw new CustomException(ErrorCode.ALREADY_VOTED, "이미 투표 완료");
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
