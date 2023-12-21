package com.sniff.springvote18th.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    LOGIN_ID_DUPLICATED(HttpStatus.CONFLICT, ""),
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, ""),
    ID_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    INVALID_VOTE(HttpStatus.UNAUTHORIZED, ""),
    TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    CANDIDATE_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    ALREADY_VOTED(HttpStatus.CONFLICT, ""),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "");

    private HttpStatus httpStatus;
    private String message;

}