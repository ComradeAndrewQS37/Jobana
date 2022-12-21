package com.example.jobana.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

enum class Role {
    ADMIN, USER
}

fun comparePassword(rawPassword: CharSequence, encodedPassword: String): Boolean {
    return BCryptPasswordEncoder().matches(rawPassword, encodedPassword)
}

object JwtManager {
    private val secret: String = "secret"

    fun getJwtBody(jwt: String): Claims =
        Jwts.parser()
            .setSigningKey(secret.toByteArray())
            .parseClaimsJws(jwt).body


    fun getJwtToken(issuer: String): String = Jwts.builder()
        .setIssuer(issuer)
        .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))
        .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
        .compact()

}



