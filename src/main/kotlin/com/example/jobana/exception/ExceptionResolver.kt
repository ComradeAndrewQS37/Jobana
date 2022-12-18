package com.example.jobana.exception

import com.example.jobana.exception.dto.AbstractApiException
import com.example.jobana.exception.dto.InternalServerException
import com.example.jobana.exception.dto.InvalidRequestDataException
import com.example.jobana.model.dto.response.ApiResponse
import org.slf4j.LoggerFactory

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ExceptionResolver {
    private val logger = LoggerFactory.getLogger(ExceptionResolver::class.java)

    @ExceptionHandler(value = [InvalidRequestDataException::class])
    protected fun handle(cause: AbstractApiException, request: WebRequest): ResponseEntity<ApiResponse> {
        logger.info(cause.stackTraceToString())
        return cause.asResponse()
    }

    @ExceptionHandler(value = [AbstractApiException::class])
    protected fun handleAbstract(cause: AbstractApiException, request: WebRequest): ResponseEntity<ApiResponse> {
        logger.info(cause.stackTraceToString())
        return cause.asResponse()
    }

    @ExceptionHandler(value = [Throwable::class])
    protected fun handleThrowable(cause: Throwable, request: WebRequest): ResponseEntity<ApiResponse> {
        logger.error(cause.stackTraceToString())
        return InternalServerException().asResponse()
    }
}
