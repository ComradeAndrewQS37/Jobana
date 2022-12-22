package com.example.jobana.security

import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

enum class Role {
    ADMIN, USER
}

fun comparePassword(rawPassword: CharSequence, encodedPassword: String): Boolean {
    return BCryptPasswordEncoder().matches(rawPassword, encodedPassword)
}
