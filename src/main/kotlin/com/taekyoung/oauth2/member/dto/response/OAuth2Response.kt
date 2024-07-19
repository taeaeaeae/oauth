package com.taekyoung.oauth2.member.dto.response

class OAuth2Response(private val attribute: Map<String, Any>) {
    val provider: String
        get() = "google"

    val providerId: String
        get() = attribute["sub"].toString()

    val email: String
        get() = attribute["email"].toString()

    val name: String
        get() = attribute["name"].toString()
}