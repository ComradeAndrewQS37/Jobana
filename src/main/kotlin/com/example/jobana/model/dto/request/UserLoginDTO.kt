package com.example.jobana.model.dto.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

class UserLoginDTO(
    val email: String,
    val password: String,
    )