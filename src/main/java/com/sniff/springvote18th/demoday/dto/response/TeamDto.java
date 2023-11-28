package com.sniff.springvote18th.demoday.dto.response;

import com.sniff.springvote18th.domain.Team;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamDto {
    private Long teamId;
    private String teamName;
    private String description;
    private int count;

    //근데 그냥 Team 엔티티랑 똑같은데 굳이 Dto로 만들어야 하나..?

    @Builder
    public TeamDto(Team team) {
        this.teamId = team.getId();
        this.teamName = team.getTeamName().name();
        this.description = team.getDescription();
        this.count = team.getCount();
    }
}
