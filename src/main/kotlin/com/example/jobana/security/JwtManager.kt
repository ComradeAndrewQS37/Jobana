package com.example.jobana.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtManager {
    @Value("\${app.security.secret}")
    val secret: String = ""

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