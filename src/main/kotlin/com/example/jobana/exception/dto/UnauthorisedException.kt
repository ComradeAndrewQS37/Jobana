package com.example.jobana.exception.dto

import org.springframework.http.HttpStatus

class UnauthorisedException(
    message : String = "Available for authorised users only",
    status: HttpStatus = HttpStatus.UNAUTHORIZED
) : AbstractApiException(status, message)