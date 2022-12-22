package com.example.jobana.service

import com.example.jobana.exception.dto.ResourceNotFoundException
import com.example.jobana.model.dto.response.AdvertResponse
import com.example.jobana.model.entities.AdvStatus
import com.example.jobana.model.entities.OfferFor
import com.example.jobana.model.entities.Response
import com.example.jobana.model.entities.User
import com.example.jobana.model.entity.Advert
import com.example.jobana.model.repository.AdvertDao
import com.example.jobana.model.repository.UserDao
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


@Service
class AdvertService(
    val advertDao: AdvertDao,
    val userDao: UserDao
    ){

    fun getAdvertsByUser(id: Long): Iterable<Advert>{
        if (!advertDao.existsById(id)) throw ResourceNotFoundException(message = "Resource not found : invalid id $id")

        return advertDao.findAdvertByAuthor_Id(id)
    }

    fun getAdvertById(id: Long): Advert {
        if (!advertDao.existsById(id)) throw ResourceNotFoundException(message = "Resource not found : invalid id $id")

        return advertDao.findById(id).get()
    }

    fun createAdvert(preEntity: Map<String, Any?>): Advert{
        val user_id = preEntity["author"].toString().toLong() //TODO Здесь надо проверку получше сделать

        if (!userDao.existsById(user_id))
            throw ResourceNotFoundException(message = "Resource not found : invalid id $user_id")

//        val advert = Advert(
//            author = userDao.findById(user_id).get(),
//            offerFor = preEntity["offerFor"] as OfferFor,
//            title = preEntity["title"].toString(),
//            description = preEntity["description"].toString(),
//            updatedAt = preEntity["updatedAt"] as LocalDateTime,
//            closedAt = preEntity["closedAt"] as LocalDateTime?,
//            place = preEntity["place"].toString(),
//            price = preEntity["price"].toString().toInt(),
//            status = preEntity["status"] as AdvStatus,
//            response = preEntity["response"] as List<Response>?
//        )

        return advertDao.save(
            Advert(
                author = userDao.findById(user_id).get(),
                offerFor = preEntity["offerFor"] as OfferFor,
                title = preEntity["title"].toString(),
                description = preEntity["description"].toString(),
                updatedAt = preEntity["updatedAt"] as LocalDateTime,
                closedAt = preEntity["closedAt"] as LocalDateTime?,
                place = preEntity["place"].toString(),
                price = preEntity["price"].toString().toInt(),
                status = preEntity["status"] as AdvStatus,
                response = preEntity["response"] as List<Response>?
            )
        )
    }


}