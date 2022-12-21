package com.example.jobana.model.dto.request

import com.example.jobana.exception.dto.InvalidRequestDataException
import com.example.jobana.model.entities.Gender
import com.example.jobana.model.entities.ReportMessages
import com.example.jobana.model.entities.SupportMessages
import com.example.jobana.model.entities.User
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

class UserRegisterDTO(
    val firstName: String,
    val lastName: String,
    @JsonFormat(pattern = "dd-MM-yyyy")
    val birthDate: LocalDate,
    val gender: String,
    val email: String,
    val password: String,

    ) {

    val supportMessages: List<SupportMessages>? = null
    val reportMessages: List<ReportMessages>? = null

    fun asEntity() = User(
        firstName,
        lastName,
        birthDate,
        gender.let {
            try {
                Gender.valueOf(it)
            } catch (e: IllegalArgumentException) {
                throw InvalidRequestDataException(message = "Invalid field value : gender")
            }
        },
        email
    ).also {
        it.password = password
    }
}