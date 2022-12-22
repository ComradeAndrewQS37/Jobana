package com.example.jobana.service

import com.example.jobana.exception.dto.InvalidRequestDataException
import com.example.jobana.exception.dto.ResourceNotFoundException
import com.example.jobana.model.entities.User
import com.example.jobana.model.repository.UserDao
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(val userDao: UserDao)  : UserDetailsService{
    fun getAll(): Iterable<User> = userDao.findAll()

    fun getById(id: Long): User? {
        if (!userDao.existsById(id)) throw ResourceNotFoundException(message = "Resource not found : invalid id $id")

        return userDao.findByIdOrNull(id)
    }


    fun createUser(user: User): User {
        val emailRegex = Regex("^[\\w-.]+@([\\w-]+.)+[\\w-]+$")
        if (!user.email.matches(emailRegex)) {
            throw InvalidRequestDataException(message = "Invalid field value : email")
        }

        val encodedPassword = BCryptPasswordEncoder().encode(user.password)
        user.password = encodedPassword
        return userDao.save(user)
    }

    fun deleteUser(id: Long) {
        if (!userDao.existsById(id)) throw ResourceNotFoundException(message = "Resource not found : invalid id $id")
        userDao.deleteById(id)
    }

    fun findByEmail(email: String) = userDao.findByEmail(email)

    // для аутентификации
     override fun loadUserByUsername(username: String): UserDetails {
        val user = userDao.findByEmail(username) ?: throw ResourceNotFoundException("No such user")


         val userDetails = org.springframework.security.core.userdetails.User
             .withUsername(username)
             .password("{bcrypt}${user.password}")
             .roles(if (user.email == "admin@gmail.com") "ADMIN" else "USER")
             .build()

        return userDetails
    }
}