package com.example.jobana.controller

import com.example.jobana.auth.JwtManager
import com.example.jobana.auth.comparePassword
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

@RestController
class AuthController(private val userService: UserService) {

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
        val jwt = JwtManager.getJwtToken(issuer)

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
        response.addCookie(cookie)

        return ResponseEntity.ok("signed in successfully")
    }

    @GetMapping("/me")
    fun userMe(@CookieValue(name = "jwt") jwt: String?): ResponseEntity<Any> {
        if (jwt == null) {
            throw UnauthorisedException()
        }

        val body = try {
            JwtManager.getJwtBody(jwt)
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