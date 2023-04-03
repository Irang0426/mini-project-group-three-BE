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

    /**
     * 사용중인 계정 에러 처리, 400
     *
     * @return the http entity
     */
    @ExceptionHandler(AlreadyRegisteredUserException.class)
    public HttpEntity<Response> alreadyRegisteredUserExceptionHandler() {

        Response response =
                new Response("400", "이미 사용중인 아이디입니다.");
        return ResponseEntity.badRequest().body(response);

    }

    /**
     * validation 실패시 에러 처리, 400
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> methodValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
        Response response = new Response("400", errorMessage);
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Aes256 암호화, 복호화 서버 에러, 500
     *
     * @return the response entity
     */
    @ExceptionHandler(AES256EncodingException.class)
    public ResponseEntity<Response> AES256EncodingException() {

        Response response = new Response("500", "서버 장애입니다.");
        return ResponseEntity.internalServerError().body(response);
    }
}