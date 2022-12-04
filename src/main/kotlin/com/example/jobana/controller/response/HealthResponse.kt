package com.example.jobana.controller.response

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class HealthResponse(
        override val status: HttpStatus = HttpStatus.OK,
        override val message: String="OK",
        override val timestamp: LocalDateTime = LocalDateTime.now()) : ApiResponse