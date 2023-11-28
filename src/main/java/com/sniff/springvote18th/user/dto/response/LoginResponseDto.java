package com.sniff.springvote18th.user.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class LoginResponseDto {
    private String teamName;
    private String part;
    private String name;
    private String accessToken;
}