package com.miniproject.miniprojectgroupthree.controller.advice.exception;

import com.miniproject.miniprojectgroupthree.dto.Response;
import com.miniproject.miniprojectgroupthree.error.AES256EncodingException;
import com.miniproject.miniprojectgroupthree.error.AlreadyRegisteredUserException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegisterExceptionAdvice {

    @ExceptionHandler(AlreadyRegisteredUserException.class)
    public HttpEntity<Response> alreadyRegisteredUserExceptionHandler() {

        Response response =
                new Response("400", "이미 사용중인 아이디입니다.");
        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> methodValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
        Response response = new Response("400", errorMessage);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AES256EncodingException.class)
    public ResponseEntity<Response> AES256EncodingException() {

        Response response = new Response("500", "서버 장애입니다.");
        return ResponseEntity.internalServerError().body(response);
    }
}