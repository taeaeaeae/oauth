package spartacodingclub.nbcamp.kotlinspring.project.team4ighting.spring4gamer.domain.member.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class mainController {

    @GetMapping("/")
    fun main(): String = "main"

    @GetMapping("/login")
    fun login(): String = "login"

    @GetMapping("/my")
    fun myPage(): String {
        return "my"
    }

}