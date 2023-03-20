package com.miniproject.miniprojectgroupthree.error;


public class AlreadyRegisteredUserException extends RuntimeException {
    public AlreadyRegisteredUserException() {
        super("이미 사용중인 아이디입니다.");
    }
}

