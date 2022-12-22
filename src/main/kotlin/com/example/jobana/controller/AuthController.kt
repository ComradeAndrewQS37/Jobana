package com.example.jobana.controller

import com.example.jobana.security.JwtManager
import com.example.jobana.security.comparePassword
import com.example.jobana.exception.dto.ForbiddenException
import com.example.jobana.exception.dto.UnauthorisedException
import com.example.jobana.model.dto.request.UserLoginDTO
import com.example.jobana.model.dto.request.UserRegisterDTO
import com.example.jobana.model.entities.User
import com.example.jobana.service.UserService
import io.jsonwebtoken.SignatureException
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.context.SecurityContextHolder

@RestController
class AuthController(private val userService: UserService, private val jwtManager: JwtManager) {

    // для тестирования
    @GetMapping("/admin")
    fun adminTest() : ResponseEntity<Any>{

        val auth = SecurityContextHolder.getContext().authentication

        return ResponseEntity.ok("You are admin : ${auth.name}")
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody request: UserRegisterDTO): ResponseEntity<User> {
        val user = request.asEntity()
        return ResponseEntity.ok(userService.createUser(user))
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody request: UserLoginDTO, response: HttpServletResponse): ResponseEntity<Any> {
        val user = userService.findByEmail(request.email)
            ?: throw ForbiddenException("Invalid username")

        if (!comparePassword(request.password, user.password)) {
            throw ForbiddenException("Invalid password")
        }

        val issuer = user.id.toString()
        val jwt = jwtManager.getJwtToken(issuer)

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
        response.addCookie(cookie)

        return ResponseEntity.ok(user)
    }

    @GetMapping("/me")
    fun userMe(@CookieValue(name = "jwt") jwt: String?): ResponseEntity<Any> {
        if (jwt == null) {
            throw UnauthorisedException()
        }

        val body = try {
            jwtManager.getJwtBody(jwt)
        } catch (e: SignatureException) {
            throw UnauthorisedException("Invalid token")
        }

        val user = userService.getById(body.issuer.toLong())
        return ResponseEntity.ok(user)
    }

    @PostMapping("/logout")
    fun logOut(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok("Logged out successfully")
    }

}