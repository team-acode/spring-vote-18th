package com.sniff.springvote18th.user.dto.request;

import lombok.Getter;

@Getter
public class SignUpDto {
    private String loginId;
    private String password;
    private String email;
    private String name;
    private String part;
    private String teamName;
}
