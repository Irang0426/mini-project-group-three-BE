package com.miniproject.miniprojectgroupthree.error;

public class InvalidMemberSaveFormException extends RuntimeException {

    /**
     * MemberSaveForm의 validator Exception 처리
     * message는 MemberSaveForm의 각자의 어노테이션의 message
     *
     * @param message the message
     */
    public InvalidMemberSaveFormException(String message) {
        super(message);
    }

}

