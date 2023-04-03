package com.miniproject.miniprojectgroupthree.controller;

import com.miniproject.miniprojectgroupthree.dto.Response;
import com.miniproject.miniprojectgroupthree.form.MemberSaveForm;
import com.miniproject.miniprojectgroupthree.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Member", description = "회원 API")
public class RegisterController {

    private final MemberService memberService;

    @PostMapping("/save")
    @Operation(summary = "회원 가입 등록/수정", description = "회원 가입 양식 및 프로필 이미지 업로드 등록/수정 처리를 한다.")
    @ApiResponse(description = "회원가입 성공에 대한 boolean 리턴")
    public HttpEntity<Response> save(@RequestBody @Validated MemberSaveForm form) {


        memberService.signup(form);

        Response response =
                new Response("200", "회원 가입 성공");
        return ResponseEntity.ok(response);
    }

}
