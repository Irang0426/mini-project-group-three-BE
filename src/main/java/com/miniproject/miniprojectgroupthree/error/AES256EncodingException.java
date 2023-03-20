package com.miniproject.miniprojectgroupthree.error;

public class AES256EncodingException extends RuntimeException {
    public AES256EncodingException(Exception e) {
        super("서버 암호화 에러입니다.");
    }
}
