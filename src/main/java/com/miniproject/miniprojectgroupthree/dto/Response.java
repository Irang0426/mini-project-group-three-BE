package com.miniproject.miniprojectgroupthree.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 서버에서 프론트로 보내는 정보
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String code;
    private String message;
}