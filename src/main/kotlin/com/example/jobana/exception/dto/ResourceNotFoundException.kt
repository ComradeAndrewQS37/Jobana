package com.example.jobana.exception.dto

import org.springframework.http.HttpStatus

class ResourceNotFoundException(
    message: String = "Resource not found",
    status: HttpStatus = HttpStatus.NOT_FOUND
) : AbstractApiException(
    status, message
)
