package com.example.jobana.exception.dto

import org.springframework.http.HttpStatus


/**если другие типы исключений не подходят то будет выброшено это**/
class InternalServerException(
    message: String = "Internal server error",
    status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR

) : AbstractApiException(status, message)