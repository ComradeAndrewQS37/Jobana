package com.example.jobana.model.dto.response.message

import org.springframework.http.HttpStatus


/**отправляется в ответ на DELETE, если ресурс успешно удалён**/
class DeletedMessage : AbstractApiMessage(
    status = HttpStatus.OK,
    message = "Successfully deleted"
)
