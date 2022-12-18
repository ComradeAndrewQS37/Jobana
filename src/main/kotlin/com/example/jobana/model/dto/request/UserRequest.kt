package com.example.jobana.model.dto.request

import com.example.jobana.exception.dto.InvalidRequestDataException
import com.example.jobana.model.entities.CV
import com.example.jobana.model.entities.Gender
import com.example.jobana.model.entities.User
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalDateTime

class UserRequest(
    val firstName: String,
    val lastName: String,
    @JsonFormat(pattern="dd-MM-yyyy")
    val birthDate: LocalDate,
    val gender: String,
    val email: String,
    val phoneNumber: String? = null,
    val cv: CV? = null,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    val lastLogIn: LocalDateTime? = null,
) {

    fun asEntity() = User(
        firstName,
        lastName,
        birthDate,
        gender.let {
            try {
                Gender.valueOf(it)
            } catch (e: IllegalArgumentException) {
                throw InvalidRequestDataException(message="Invalid field value : gender")
            }
        },
        email,
        phoneNumber,
        cv,
        lastLogIn
    )
}