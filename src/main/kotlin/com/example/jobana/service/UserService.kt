package com.example.jobana.service

import com.example.jobana.exception.dto.InvalidRequestDataException
import com.example.jobana.exception.dto.ResourceNotFoundException
import com.example.jobana.model.repository.UserDao
import com.example.jobana.model.entities.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class UserService(val userDao: UserDao) {
    fun getAll(): Iterable<User> = userDao.findAll()

    fun getById(id: Long) : User? {
        if (!userDao.existsById(id)) throw ResourceNotFoundException(message = "Resource not found : invalid id $id")

        return userDao.findByIdOrNull(id)
    }


    fun createUser(user: User): User {
        val emailRegex = Regex("^[\\w-.]+@([\\w-]+.)+[\\w-]+$")
        if (!user.email.matches(emailRegex)) {
            throw InvalidRequestDataException(message = "Invalid field value : email")
        }

        return userDao.save(user)
    }

    fun deleteUser(id: Long) {
        if (!userDao.existsById(id)) throw ResourceNotFoundException(message="Resource not found : invalid id $id")
        userDao.deleteById(id)
    }

    fun findByEmail(email : String) = userDao.findByEmail(email)
}