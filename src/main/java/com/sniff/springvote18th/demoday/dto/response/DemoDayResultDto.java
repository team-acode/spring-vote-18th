package com.sniff.springvote18th.demoday.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DemoDayResultDto {
    private List<TeamDto> teamList;

    @Builder
    public DemoDayResultDto(List<TeamDto> teamDtos) {
        this.teamList = teamDtos;
    }
}
