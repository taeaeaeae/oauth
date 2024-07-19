package com.taekyoung.oauth2.member.dto.response

import com.taekyoung.oauth2.member.service.Member
import java.util.UUID

class MemberSimplifiedResponse(
    val id: UUID,
    val nickname: String
) {

    companion object {
        fun from(member: Member): MemberSimplifiedResponse =
            MemberSimplifiedResponse(member.id, member.nickname)
    }
}