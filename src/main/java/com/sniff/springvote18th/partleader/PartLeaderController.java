package com.sniff.springvote18th.partleader;

import com.sniff.springvote18th.domain.User;
import com.sniff.springvote18th.partleader.dto.CandidateResponseDto;
import com.sniff.springvote18th.partleader.dto.PartLeaderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/part-leader")
@RequiredArgsConstructor
public class PartLeaderController {

    private final PartLeaderService partLeaderService;

    @GetMapping("/votes")
    public CandidateResponseDto getCandidateList(@RequestParam String part){
        return partLeaderService.getCandidateResult(part);
    }

    @PostMapping("/votes")
    public ResponseEntity votePartLeader(@RequestParam String part, @RequestBody PartLeaderRequestDto dto, @AuthenticationPrincipal User user){
        return partLeaderService.votePartLeader(part, dto, user);
    }


    @GetMapping("/results")
    public CandidateResponseDto getCandidateListDesc(@RequestParam String part){
        return partLeaderService.getCandidateResultDesc(part);
    }

}
