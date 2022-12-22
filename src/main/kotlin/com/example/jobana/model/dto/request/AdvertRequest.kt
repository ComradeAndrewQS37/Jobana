package com.example.jobana.model.dto.request

import com.example.jobana.exception.dto.InvalidRequestDataException
import com.example.jobana.model.entities.AdvStatus
import com.example.jobana.model.entities.OfferFor
import com.example.jobana.model.entities.Response
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class AdvertRequest(
    val author: Long,
    val offerFor: String,
    val title: String,
    val description: String,
    val place: String,
    val price: Int,
) {

    val response: List<Response>? = null
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime = LocalDateTime.now()
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    val closedAt: LocalDateTime? = null
    val status : AdvStatus = AdvStatus.Opened


    fun asPreEntity() = mapOf(
        "author" to author,
        "offerFor" to offerFor.let {
            try {
                OfferFor.valueOf(it)
            } catch (e: IllegalArgumentException) {
                throw InvalidRequestDataException(message="Invalid field value : offerFor")
            }
        },
        "title" to title,
        "description" to description,
        "updatedAt" to updatedAt,
        "closedAt" to closedAt,
        "place" to place,
        "price" to price,
        "status" to status,
        "response" to response
    )

}