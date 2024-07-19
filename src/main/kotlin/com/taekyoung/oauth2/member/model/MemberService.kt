package com.taekyoung.oauth2.member.model

import com.taekyoung.oauth2.member.dto.response.MemberResponse
import com.taekyoung.oauth2.member.dto.response.MemberSimplifiedResponse
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import com.taekyoung.oauth2.member.repository.MemberRepository
import java.util.UUID

@Service
class MemberService(
    private val repository: MemberRepository
) {

    fun getMember(id: UUID): MemberResponse {
        val member = repository.findByIdOrNull(id) ?: throw EntityNotFoundException("model not found")
        return MemberResponse.from(member)
    }

    fun getSimpleMember(id: UUID): MemberSimplifiedResponse {
        val member = repository.findByIdOrNull(id) ?: throw EntityNotFoundException("model not found")
        return MemberSimplifiedResponse.from(member)
    }
}