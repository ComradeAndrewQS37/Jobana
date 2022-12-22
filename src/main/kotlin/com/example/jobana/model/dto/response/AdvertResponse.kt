package com.example.jobana.model.dto.response

import com.example.jobana.model.entities.AdvStatus
import com.example.jobana.model.entities.Response
import com.example.jobana.model.entity.Advert
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class AdvertResponse(
//    val author: Long,
//    val offerFor: String,
//    val title: String,
//    val description: String,
//    val place: String,
//    val price: Int,
//    //val response: List<Response>, их не нужно показывать
//    val updatedAt: String,
//    val closedAt: String?,
//    val status : String,
    val advertData: AdvertData,
    override val status: HttpStatus = HttpStatus.OK,
    override val message: String="There is an advert",
    override val timestamp: LocalDateTime = LocalDateTime.now()

    ) : ApiResponse


class AdvertData(
    val author_id: Long,
    val offerFor: String,
    val title: String,
    val description: String,
    val place: String,
    val price: Int,
    //val response: List<Response>, их не нужно показывать
    val updatedAt: String,
    val closedAt: String?,
    val advStatus : String,
)

fun Advert.asData() = AdvertData(
    author_id = this.author.id,
    offerFor = this.offerFor.toString(),
    title = this.title,
    description = this.description,
    place = this.place,
    price = this.price,
    updatedAt = this.updatedAt.toString(),
    closedAt = this.closedAt?.toString(),
    advStatus = this.status.toString()
)

//fun Advert.asResponse() = AdvertResponse(this.asData()).asResponse()