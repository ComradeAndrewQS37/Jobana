package com.example.jobana.model.dto

import com.example.jobana.exception.dto.ResourceNotFoundException
import com.example.jobana.model.entities.User
import com.example.jobana.model.repository.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

//Класс чисто для переводов одних типов в другие
@Component
class dtoUtils(
    val userDao: UserDao
) {

    fun asUser(id: Long) : User? {
        val userDao: UserDao

        if (!userDao.existsById(id)) throw ResourceNotFoundException(message = "Resource not found : invalid id $id")

        return userDao.findByIdOrNull(id)
    }
}