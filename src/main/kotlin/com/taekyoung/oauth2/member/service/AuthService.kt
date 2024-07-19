package com.taekyoung.oauth2.member.service

import com.taekyoung.oauth2.infra.security.jwt.JwtHelper
import com.taekyoung.oauth2.member.dto.request.SigninRequest
import com.taekyoung.oauth2.member.dto.request.SignupRequest
import com.taekyoung.oauth2.member.dto.response.MemberResponse
import com.taekyoung.oauth2.member.dto.response.SigninResponse
import com.taekyoung.oauth2.member.model.MemberRole
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import com.taekyoung.oauth2.member.repository.MemberRepository

@Service
class AuthService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtHelper: JwtHelper
) {

    @Transactional
    fun signup(request: SignupRequest): MemberResponse {
        if (memberRepository.existsByEmail(request.email)) throw IllegalArgumentException("이미 가입된 이메일")
        if (memberRepository.existsByNickname(request.nickname)) throw IllegalArgumentException("이미 존재하는 닉네임")
        return Member.from(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            nickname = request.nickname,
        ).let { memberRepository.save(it) }
            .let { MemberResponse.from(it) }
    }

    fun signin(request: SigninRequest): SigninResponse {
        val member = memberRepository.findByEmail(request.email) ?: throw IllegalArgumentException("회원정보를 찾을 수 없습니다.")
        if (!passwordEncoder.matches(
                request.password,
                member.password
            )
        ) throw IllegalArgumentException("password가 일치하지 않습니다.")
        return SigninResponse(
            jwtHelper.generateAccessToken(
                subject = member.id.toString(),
                email = member.email,
                role = MemberRole.USER.name
            )
        )
    }

//    fun googleSignin(accessToken: String): String {
//
//    }

}