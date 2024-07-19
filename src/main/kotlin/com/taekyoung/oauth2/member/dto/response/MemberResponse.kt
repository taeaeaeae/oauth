package com.taekyoung.oauth2.member.dto.response

import com.taekyoung.oauth2.member.service.Member
import java.util.UUID

data class MemberResponse(
    val id: UUID,
    val email: String,
    val nickname: String
) {

    companion object {
        fun from(member: Member): MemberResponse {
            return MemberResponse(member.id, member.email, member.nickname)
        }
    }
}