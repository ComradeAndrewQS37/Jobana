package com.example.jobana.model.dto.response

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class HealthResponse(
        override val status: HttpStatus = HttpStatus.OK,
        override val message: String="API is working",
        override val timestamp: LocalDateTime = LocalDateTime.now()
) : ApiResponse