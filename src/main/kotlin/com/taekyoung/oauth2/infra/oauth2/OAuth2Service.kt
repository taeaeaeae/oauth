package com.taekyoung.oauth2.infra.oauth2

import com.taekyoung.oauth2.member.dto.response.OAuth2Response
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import com.taekyoung.oauth2.member.repository.MemberRepository
import com.taekyoung.oauth2.member.service.Member
import org.springframework.security.crypto.password.PasswordEncoder


@Service
class OAuth2Service(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {

        val oAuth2User = super.loadUser(userRequest)
        println(oAuth2User.attributes)

        val oAuth2Response = OAuth2Response(oAuth2User.attributes)

        val username = oAuth2Response.provider + " " + oAuth2Response.providerId
        val existData = memberRepository.findByEmail(oAuth2Response.email)

        val role : String

        if(existData == null) {
            val member = Member.from(oAuth2Response.email, passwordEncoder.encode(username), username)
            memberRepository.save(member)
            role = "ROLE_${member.role.name}"
        } else {
            role = "ROLE_${existData.role.name}"
        }

        return CustomOAuth2User(oAuth2Response, role)
    }
}