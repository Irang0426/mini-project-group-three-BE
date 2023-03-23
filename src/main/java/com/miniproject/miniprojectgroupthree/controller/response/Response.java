package com.miniproject.miniprojectgroupthree.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultCode;
    private T result;

    public static Response<Void> error(String errorCode) {
        return new Response<>(errorCode,null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<>("success", result);
    }

//    public static Response<Void> error(String errorCode, String message) {
//        return new Response<>(errorCode,message);
//    }
}
