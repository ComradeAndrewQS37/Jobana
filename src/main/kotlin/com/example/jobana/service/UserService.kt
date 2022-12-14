package com.example.jobana.service

import com.example.jobana.model.repository.UserDao
import com.example.jobana.model.entities.User
import org.springframework.stereotype.Service


@Service
class UserService (val userDao: UserDao) {
    fun getAll() : Iterable<User> = userDao.findAll()

    fun getById(id : Long) = userDao.findById(id)
}