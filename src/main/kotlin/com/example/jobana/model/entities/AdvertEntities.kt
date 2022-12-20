package com.example.jobana.model.entity

import com.example.jobana.model.entities.AbstractEntity
import com.example.jobana.model.entities.Reason
import com.example.jobana.model.entities.User
import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime

//TODO хранение фото
@MappedSuperclass
abstract class AdvertEntity (
    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    var author: User,

    @Column(nullable = false, length = 64)
    var title : String,

    @Column(nullable = false, length = 255)
    var description : String,

    @Column(nullable = true)
    var updatedAt : LocalDateTime,

    @Column(nullable = true)
    var closedAt : LocalDateTime,

    /* Возможно, переделать на хранение геоданных как-то так
    https://medium.datadriveninvestor.com/getting-started-building-location-based-gis-rest-apis-with-java-ca28a8869af3
     */
    @Column(nullable = false, length = 128)
    var place : String,

    @Column(nullable = false)
    var price : Int

) : AbstractEntity()

@Entity
@Table(name="ExpertsAdverts")
class ExpertsAdverts (
    author: User, title: String, description: String, updatedAt: LocalDateTime, closedAt: LocalDateTime, place: String, price: Int,

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    var closedReason : Reason


) : AdvertEntity(author, title, description, updatedAt, closedAt, place, price)

@Entity
@Table(name="ClientsAdverts")
class ClientsAdverts (
    author: User, title: String, description: String, updatedAt: LocalDateTime, closedAt: LocalDateTime, place: String, price: Int,

    @Column(nullable = true)
    var isBlocked : Boolean

) : AdvertEntity(author, title, description, updatedAt, closedAt, place, price)

@Entity
@Table(name="Responses")
class Response (
    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    var isAccepted : Boolean,

    @ManyToOne
    @JoinColumn(name="expert_advert_id", nullable = true)
    val expertAdvert : ExpertsAdverts,

    //TODO как делать етот .... constraint

    @ManyToOne
    @JoinColumn(name="expert_advert_id", nullable = true)
    val clientAdvert : ClientsAdverts,

) : AbstractEntity()