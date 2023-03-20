package com.miniproject.miniprojectgroupthree.error;

public class AES256EncodingException extends RuntimeException {
    /**
     * 서버 암호화, 복호화 시 에러.
     *
     * @param e the e
     */
    public AES256EncodingException(Exception e) {
        super("서버 암호화 에러입니다.");
    }
}
