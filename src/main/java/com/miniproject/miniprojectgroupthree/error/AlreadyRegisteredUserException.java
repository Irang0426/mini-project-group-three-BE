package com.miniproject.miniprojectgroupthree.error;


public class AlreadyRegisteredUserException extends RuntimeException {
    /**
     * 이미 사용중인 계정일 경우 발생하는 에러
     */
    public AlreadyRegisteredUserException() {
        super("이미 사용중인 계정입니다.");
    }
}

