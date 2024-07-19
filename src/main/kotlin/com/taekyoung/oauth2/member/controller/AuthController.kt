package com.taekyoung.oauth2.member.controller

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

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    val authService: AuthService,
    val oauth2UserService: OAuth2Service,
) {

    @PostMapping("/signin")
    fun signin(@RequestBody request: SigninRequest): ResponseEntity<SigninResponse> =
        ResponseEntity.status(HttpStatus.OK).body(authService.signin(request))


    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseEntity<MemberResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(request))

    @GetMapping("/signin/google")
    fun googleSignup(@AuthenticationPrincipal principal: OAuth2User?, model: Model): ResponseEntity<String> {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.OK).body("dksemfdjdha")
        }
        return ResponseEntity.status(HttpStatus.OK).body("로그인${model.getAttribute("email")}")
    }

}