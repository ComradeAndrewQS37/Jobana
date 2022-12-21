package com.example.jobana.service

import com.example.jobana.exception.dto.ResourceNotFoundException
import com.example.jobana.model.entity.Advert
import com.example.jobana.model.repository.AdvertDao
import org.springframework.stereotype.Service
import java.util.*


@Service
class AdvertService(val advertDao: AdvertDao){

    fun getAdvertsByUser(id: Long): Iterable<Advert>{
        if (!advertDao.existsById(id)) throw ResourceNotFoundException(message = "Resource not found : invalid id $id")

        return advertDao.findAdvertByAuthor_Id(id)
    }

    fun getAdvertById(id: Long): Optional<Advert> {
        if (!advertDao.existsById(id)) throw ResourceNotFoundException(message = "Resource not found : invalid id $id")

        return advertDao.findById(id)
    }



}