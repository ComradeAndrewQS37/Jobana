package com.example.jobana.exception.dto

import org.springframework.http.HttpStatus

class InvalidRequestDataException (
    status: HttpStatus = HttpStatus.UNPROCESSABLE_ENTITY,
    message : String = "Unable to process the request"
) : AbstractApiException(status, message)