package spartacodingclub.nbcamp.kotlinspring.project.team4ighting.spring4gamer.domain.member.controller

import jakarta.servlet.http.HttpServletResponse
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
import spartacodingclub.nbcamp.kotlinspring.project.team4ighting.spring4gamer.domain.member.dto.response.MemberResponse
import spartacodingclub.nbcamp.kotlinspring.project.team4ighting.spring4gamer.domain.member.dto.request.SigninRequest
import spartacodingclub.nbcamp.kotlinspring.project.team4ighting.spring4gamer.domain.member.dto.response.SigninResponse
import spartacodingclub.nbcamp.kotlinspring.project.team4ighting.spring4gamer.domain.member.dto.request.SignupRequest
import spartacodingclub.nbcamp.kotlinspring.project.team4ighting.spring4gamer.domain.member.service.AuthService
import spartacodingclub.nbcamp.kotlinspring.project.team4ighting.spring4gamer.infra.oauth2.OAuth2Service

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