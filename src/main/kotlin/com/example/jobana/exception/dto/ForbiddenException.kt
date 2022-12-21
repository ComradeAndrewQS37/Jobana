package com.example.jobana.exception.dto

import org.springframework.http.HttpStatus

/**неправильные логин или пароль**/
class ForbiddenException(
    message : String = "Wrong username or password",
    status: HttpStatus = HttpStatus.FORBIDDEN
) : AbstractApiException(status, message)