package com.example.jobana.model.entity

import jakarta.persistence.*
import org.intellij.lang.annotations.Pattern
import java.time.LocalDate

// тестовый коммит 1
@Entity
@Table(name = "Users")
class User(
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
        @Pattern(value = "")
        var email : String,

        @OneToOne
        @JoinColumn(name="cv_id", referencedColumnName = "id")
        var cv : CV


) : AbstractEntity()


@Entity
@Table(name = "CV")
class CV(
        @OneToOne(mappedBy = "cv")
        var user: User,

        @Column(length = 1024)
        var hardSkills : String,

        @Column(length = 1024)
        var softSkills : String,

        @Column(length = 1024)
        var aboutMyself : String,

) : AbstractEntity()