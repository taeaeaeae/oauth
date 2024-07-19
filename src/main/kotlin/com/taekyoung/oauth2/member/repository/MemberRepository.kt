package com.taekyoung.oauth2.member.repository

import com.taekyoung.oauth2.member.service.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberRepository : JpaRepository<Member, UUID> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Member?
    fun existsByNickname(nickname: String): Boolean
}