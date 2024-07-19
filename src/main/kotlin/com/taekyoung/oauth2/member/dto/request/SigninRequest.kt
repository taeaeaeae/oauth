package com.taekyoung.oauth2.member.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SigninRequest(
    @field: Email
    @field: NotBlank
    val email: String,
    @field: NotBlank
    val password: String
)