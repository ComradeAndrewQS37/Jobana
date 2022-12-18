package com.example.jobana.exception.dto

import org.springframework.http.HttpStatus

class ResourceNotFoundException(
    status: HttpStatus = HttpStatus.NOT_FOUND,
    message: String = "Resource not found"
) : AbstractApiException(
    status, message
)
