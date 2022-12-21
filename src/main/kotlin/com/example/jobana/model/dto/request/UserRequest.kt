package com.example.jobana.model.dto.request

import com.example.jobana.exception.dto.InvalidRequestDataException
import com.example.jobana.model.entities.*
import com.example.jobana.model.entity.Advert
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

    val supportMessages: List<SupportMessages>? = null
    val reportMessages: List<ReportMessages>? = null
    val adverts: List<Advert>? = null

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
        lastLogIn,
        supportMessages,
        reportMessages,
        adverts
    )


}