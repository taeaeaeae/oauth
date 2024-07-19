package com.taekyoung.oauth2.member.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.taekyoung.oauth2.member.model.MemberService
import com.taekyoung.oauth2.infra.security.MemberPrincipal
import com.taekyoung.oauth2.member.dto.response.MemberResponse
import com.taekyoung.oauth2.member.dto.response.MemberSimplifiedResponse
import java.util.UUID

@RestController
@RequestMapping("/api/v1")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/member")
    fun getMember(@AuthenticationPrincipal principal: MemberPrincipal): ResponseEntity<MemberResponse> =
        ResponseEntity.status(HttpStatus.OK).body(memberService.getMember(principal.id))

    @GetMapping("/members/{id}")
    fun getSimpleMember(@PathVariable id: String): ResponseEntity<MemberSimplifiedResponse> =
        ResponseEntity.status(HttpStatus.OK).body(memberService.getSimpleMember(UUID.fromString(id)))

}