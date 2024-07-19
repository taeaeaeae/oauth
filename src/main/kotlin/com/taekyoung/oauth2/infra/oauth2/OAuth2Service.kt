package com.taekyoung.oauth2.infra.oauth2

import com.taekyoung.oauth2.member.dto.response.OAuth2Response
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import com.taekyoung.oauth2.member.repository.MemberRepository
import com.taekyoung.oauth2.member.service.Member


@Service
class OAuth2Service(
    private val memberRepository: MemberRepository
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {

        val oAuth2User = super.loadUser(userRequest)
        println(oAuth2User.attributes)

        val registrationId = userRequest.clientRegistration.registrationId
        val oAuth2Response = OAuth2Response(oAuth2User.attributes) //: OAuth2Response? = null

        val username = oAuth2Response.provider + " " + oAuth2Response.providerId
        val existData = memberRepository.existsByEmail(oAuth2Response.email)

        val role = "USER"

        val member = Member.from(oAuth2Response.email, "11111111", oAuth2Response.email)

        memberRepository.save(member)

        return CustomOAuth2User(oAuth2Response, role)

        //추후 작성
    }
}