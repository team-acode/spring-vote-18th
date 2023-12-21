package com.sniff.springvote18th.user.dto.response;

import com.sniff.springvote18th.domain.User;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginResponseDto {
    private String teamName;
    private String part;
    private String name;
    private boolean isTeamVoted;
    private boolean isCandidateVoted;

    private String accessToken;

    public LoginResponseDto(User user, String accessToken) {
        this.teamName = user.getTeamName().toString();
        this.part = user.getPart().toString();
        this.name = user.getName();
        this.isTeamVoted = user.isTeamVoted();
        this.isCandidateVoted = user.isCandidateVoted();

        this.accessToken = accessToken;
    }
}