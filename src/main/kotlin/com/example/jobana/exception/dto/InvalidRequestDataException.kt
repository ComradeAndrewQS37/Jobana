package com.example.jobana.exception.dto

import org.springframework.http.HttpStatus


/** если в запросе некорректные данные (например, неподходящий email или дата)**/
class InvalidRequestDataException(
    message: String = "Unable to process the request",
    status: HttpStatus = HttpStatus.UNPROCESSABLE_ENTITY
) : AbstractApiException(status, message)