package com.example.jobana.model.dto.response.message

import org.springframework.http.HttpStatus

class DeletedMessage : AbstractApiMessage(
    status = HttpStatus.OK,
    message = "Successfully deleted"
)
