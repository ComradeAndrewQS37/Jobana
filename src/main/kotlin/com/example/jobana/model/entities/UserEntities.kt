package com.example.jobana.model.entities

import com.example.jobana.model.entity.Advert
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
@Table(name = "Users")
class User(
        @Column(nullable = false, length = 128)
        var firstName : String, // имя

        @Column(nullable = false, length = 128)
        var lastName : String, // фамилия

        @Column(nullable = false)
        var birthDate : LocalDate,

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        var gender : Gender,

        @Column(unique = true, nullable = false, length = 320)
        @Pattern(regexp = "^[\\w-.]+@([\\w-]+.)+[\\w-]+$")
        var email : String,

        // TODO Как хранить номер телефона по-человечески? Может только +7?
        @Column(unique = true, length = 20)
        var phoneNumber : String?,

        @OneToOne
        @JoinColumn(name="cv_id", referencedColumnName = "id")
        var cv : CV?, // резюме

        @Column
        var lastLogIn : LocalDateTime?,

        @JsonIgnore
        @OneToMany(mappedBy="user")
        var supportMessages: List<SupportMessages>?,

        @JsonIgnore
        @OneToMany(mappedBy="user")
        var reportMessages: List<ReportMessages>?,

        @JsonIgnore
        @OneToMany(mappedBy="author")
        var adverts: List<Advert>?

) : AbstractEntity(){

}


// Резюме
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

        @OneToMany(mappedBy="cv")
        var educations: List<Education>,

        @OneToMany(mappedBy="cv")
        var experiences: List<Experience>,

) : AbstractEntity()


// Полученное образование
@Entity
@Table(name = "Educations")
class Education(

        @ManyToOne
        @JoinColumn(name="cv_id", nullable = false)
        var cv: CV,

        var educationOrganisation : String,

        var specialty : String,

        @Min(value = 1900)
        var yearOfEntry : Int,

        @Min(value = 1900)
        var graduationYear : Int

) : AbstractEntity()

// Опыт работы
@Entity
@Table(name = "Experiences")
class Experience(

        @ManyToOne
        @JoinColumn(name="cv_id", nullable = false)
        var cv: CV,

        @Column
        var started : LocalDate,

        @Column
        var ended : LocalDate,

        @Column
        var isWorking : Boolean,

        @Column(nullable = false, length = 64)
        var position : String,

        @Column(length = 64)
        var organisation : String,

        @Column(length = 256)
        var duties : String,

) : AbstractEntity()
