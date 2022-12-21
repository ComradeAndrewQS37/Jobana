package com.example.jobana.model.dto.request

import com.example.jobana.model.dto.dtoUtils
import com.example.jobana.model.entities.AdvStatus
import com.example.jobana.model.entities.Response
import com.example.jobana.model.entity.Advert
import com.example.jobana.model.repository.UserDao
import com.example.jobana.service.UserService
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.beans.factory.annotation.Autowired
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

    //TODO какая дичь, которая мне нужна для проверки на внешний ключ
    lateinit var userService: UserService

    @Autowired
    fun a(userService: UserService){
        this.userService = userService
    }


    fun asEntity = Advert(
        dtoUtils.asUser(author, userDao)!!,


    )


}