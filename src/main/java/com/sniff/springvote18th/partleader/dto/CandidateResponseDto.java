package com.sniff.springvote18th.partleader.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CandidateResponseDto {
    private List<CandidateDto> candidateList;

    @Builder
    public CandidateResponseDto(List<CandidateDto> candidateDtos){ this.candidateList = candidateDtos; }

}
