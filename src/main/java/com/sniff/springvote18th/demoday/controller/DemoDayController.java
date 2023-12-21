package com.sniff.springvote18th.demoday.controller;

import com.sniff.springvote18th.demoday.dto.request.TeamVoteDto;
import com.sniff.springvote18th.demoday.dto.response.DemoDayResultDto;
import com.sniff.springvote18th.demoday.service.DemoDayService;
import com.sniff.springvote18th.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demoday")
@RequiredArgsConstructor
public class DemoDayController {
    private final DemoDayService demoDayService;

    @GetMapping("/votes")
    public DemoDayResultDto getTeamList() {
        return demoDayService.getTeamList();
    }

    @PostMapping("/votes")
    public ResponseEntity<Void> demoDayVote(@RequestBody TeamVoteDto teamVoteDto, @AuthenticationPrincipal User user) {
        return demoDayService.demoDayVote(teamVoteDto, user);
    }

    @GetMapping("/results")
    public DemoDayResultDto getDemoDayVoteResult() {
        return demoDayService.getTeamList();
    }
}
