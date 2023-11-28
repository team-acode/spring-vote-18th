package com.sniff.springvote18th.demoday.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DemoDayResultDto {
    private List<TeamDto> teamList;

    @Builder
    public DemoDayResultDto(List<TeamDto> teamDtos) {
        this.teamList = teamDtos;
    }
}
