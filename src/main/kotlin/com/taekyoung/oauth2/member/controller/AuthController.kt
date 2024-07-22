package com.taekyoung.oauth2.member.controller

import com.taekyoung.oauth2.infra.oauth2.CustomOAuth2User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.taekyoung.oauth2.member.service.AuthService
import com.taekyoung.oauth2.infra.oauth2.OAuth2Service
import com.taekyoung.oauth2.member.dto.request.SigninRequest
import com.taekyoung.oauth2.member.dto.request.SignupRequest
import com.taekyoung.oauth2.member.dto.response.MemberResponse
import com.taekyoung.oauth2.member.dto.response.SigninResponse
import jakarta.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    val authService: AuthService
) {

    @PostMapping("/signin")
    fun signin(@RequestBody request: SigninRequest): ResponseEntity<SigninResponse> =
        ResponseEntity.status(HttpStatus.OK).body(authService.signin(request))


    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseEntity<MemberResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(request))

    @GetMapping("/signin/google")
    fun googleSignup(@AuthenticationPrincipal principal : CustomOAuth2User): ResponseEntity<SigninResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(authService.googleSignin(principal.email()))
    }

}