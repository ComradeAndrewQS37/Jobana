package com.example.jobana.service

import com.example.jobana.exception.dto.ResourceNotFoundException
import com.example.jobana.model.entities.Response
import com.example.jobana.model.repository.ResponseDao
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ResponseService(val responseDao: ResponseDao) {

    fun getAll(): Iterable<Response> = responseDao.findAll()

    fun getById(id: Long) : Response? {
        if (!responseDao.existsById(id)) throw ResourceNotFoundException(message = "Resource not found : invalid id $id")

        return responseDao.findByIdOrNull(id)
    }
/*
    fun findByAdvertId(advertId: Long) : Iterable<Response> {
        return responseDao.findAll(responseDao.)
    }*/
}