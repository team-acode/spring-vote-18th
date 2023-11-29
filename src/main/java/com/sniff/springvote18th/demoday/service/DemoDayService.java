package com.sniff.springvote18th.demoday.service;

import com.sniff.springvote18th.demoday.dto.request.TeamVoteDto;
import com.sniff.springvote18th.demoday.dto.response.DemoDayResultDto;
import com.sniff.springvote18th.demoday.dto.response.TeamDto;
import com.sniff.springvote18th.domain.Team;
import com.sniff.springvote18th.domain.TeamName;
import com.sniff.springvote18th.domain.TeamVote;
import com.sniff.springvote18th.domain.User;
import com.sniff.springvote18th.exception.CustomException;
import com.sniff.springvote18th.exception.ErrorCode;
import com.sniff.springvote18th.repository.TeamRepository;
import com.sniff.springvote18th.repository.TeamVoteRepository;
import com.sniff.springvote18th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DemoDayService {
    private final TeamVoteRepository teamVoteRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public DemoDayResultDto getTeamList() {
        List<Team> teamList = teamRepository.getTeamList();
        List<TeamDto> teamDtos = teamList.stream().map(TeamDto::new).toList();
        return new DemoDayResultDto(teamDtos);
    }

    @Transactional
    public ResponseEntity<Void> demoDayVote(TeamVoteDto teamVoteDto, User user) {
        if (!user.isTeamVoted()) {
            Team team = teamRepository.findByTeamName(TeamName.valueOf(teamVoteDto.getTeamName()))
                    .orElseThrow(() -> new CustomException(ErrorCode.TEAM_NOT_FOUND, "팀 정보 없음"));

            if (user.getTeamName().name().equals(teamVoteDto.getTeamName())) {
                throw new CustomException(ErrorCode.INVALID_VOTE, "소속 팀에게는 투표할 수 없음");
            }

            teamVoteRepository.save(TeamVote.builder()
                    .user(user)
                    .team(team)
                    .build());

            userRepository.updateIsTeamVoted(user.getId());
            teamRepository.updateVoteCount(TeamName.valueOf(teamVoteDto.getTeamName()));
        } else {
            throw new CustomException(ErrorCode.ALREADY_VOTED, "이미 투표 완료");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
