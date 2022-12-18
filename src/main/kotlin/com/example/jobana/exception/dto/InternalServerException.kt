package com.example.jobana.exception.dto

import org.springframework.http.HttpStatus

class InternalServerException(
    status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    message : String = "Internal server error"
) : AbstractApiException(status, message)