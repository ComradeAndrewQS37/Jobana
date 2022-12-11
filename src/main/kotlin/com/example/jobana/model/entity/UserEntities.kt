package com.example.jobana.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.LocalDate

// тестовый коммит 1
@Entity
class UserEntity(
        @Column(nullable = false, length = 128)
        var firstName : String,

        @Column(nullable = true, length = 128)
        var secondName : String,

        @Column(nullable = false, length = 128)
        var lastName : String,

        @Column(nullable = false)
        var birthDate : LocalDate,

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        var gender : Gender,


        @Column(unique = true, nullable = false, length = 320)
        var email : String


) : AbstractEntity()