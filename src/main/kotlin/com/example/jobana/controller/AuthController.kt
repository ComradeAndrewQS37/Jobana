package com.example.jobana.controller

import com.example.jobana.model.dto.request.UserLoginDTO
import com.example.jobana.model.dto.request.UserRegisterDTO
import com.example.jobana.model.entities.User
import com.example.jobana.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.logging.log4j.message.Message
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class AuthController(private val userService: UserService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: UserRegisterDTO): ResponseEntity<User> {
        val user = request.asEntity()
        return ResponseEntity.ok(userService.createUser(user))
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody request: UserLoginDTO): ResponseEntity<Any> {
        val user = userService.findByEmail(request.email)
            ?: return ResponseEntity.badRequest().body("User not found") //TODO Exception

        if (!user.comparePassword(request.password)) {
            return ResponseEntity.badRequest().body("Wrong password") //TODO Exception
        }

        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))
            .signWith(SignatureAlgorithm.HS512,"secret")
            .compact()

        return ResponseEntity.ok(jwt)
    }

}