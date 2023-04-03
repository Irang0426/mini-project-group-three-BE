package com.miniproject.miniprojectgroupthree.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    //이미 가입된 케이스
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "Duplicated user name"),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Token is Invalid"),
    ;

    private final HttpStatus status;
    private final String message;




    }
