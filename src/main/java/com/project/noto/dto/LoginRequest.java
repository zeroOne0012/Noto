package com.project.noto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @JsonProperty("member_id")
    @NotBlank(message = "아이디는 필수입니다.")
    private String memberId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
