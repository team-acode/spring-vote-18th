package com.sniff.springvote18th.partleader.dto;

import com.sniff.springvote18th.domain.Candidate;
import com.sniff.springvote18th.domain.Part;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CandidateDto {
    private Long id;
    private String name;
    private Part part;
    private int count;

    @Builder
    public CandidateDto(Candidate candidate){
        this.id = candidate.getId();
        this.name = candidate.getName();
        this.part = candidate.getPart();
        this.count = candidate.getCount();
    }
}
