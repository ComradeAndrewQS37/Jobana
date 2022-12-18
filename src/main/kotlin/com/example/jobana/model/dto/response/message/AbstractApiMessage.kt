package com.example.jobana.model.dto.response.message

import com.example.jobana.model.dto.response.ApiResponse
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

abstract class AbstractApiMessage(
    override val status: HttpStatus,
    override val message: String
) : ApiResponse {
    override val timestamp: LocalDateTime = LocalDateTime.now()
}
