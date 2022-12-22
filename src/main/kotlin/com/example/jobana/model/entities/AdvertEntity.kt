package com.example.jobana.model.entity

import com.example.jobana.model.entities.*
import jakarta.persistence.*
import java.time.LocalDateTime

//@MappedSuperclass
//abstract class AdvertEntity (
//    @ManyToOne
//    @JoinColumn(name="author_id", nullable = false)
//    var author: User,
//
//    @Column(nullable = false, length = 64)
//    var title : String,
//
//    @Column(nullable = false, length = 255)
//    var description : String,
//
//    @Column(nullable = true)
//    var updatedAt : LocalDateTime,
//
//    @Column(nullable = true)
//    var closedAt : LocalDateTime,
//
//    /* Возможно, переделать на хранение геоданных как-то так
//    https://medium.datadriveninvestor.com/getting-started-building-location-based-gis-rest-apis-with-java-ca28a8869af3
//     */
//    @Column(nullable = false, length = 128)
//    var place : String,
//
//    @Column(nullable = false)
//    var price : Int
//
//) : AbstractEntity()
//
//@Entity
//@Table(name="ExpertsAdverts")
//class ExpertsAdverts (
//    author: User, title: String, description: String, updatedAt: LocalDateTime, closedAt: LocalDateTime, place: String, price: Int,
//
//    @Column(nullable = true)
//    @Enumerated(EnumType.STRING)
//    var closedReason : Reason,
//
//    @OneToMany(mappedBy="expertAdvert")
//    var response: List<Response>?
//
//
//) : AdvertEntity(author, title, description, updatedAt, closedAt, place, price)
//
//@Entity
//@Table(name="ClientsAdverts")
//class ClientsAdverts (
//    author: User, title: String, description: String, updatedAt: LocalDateTime, closedAt: LocalDateTime, place: String, price: Int,
//
//    @Column(nullable = true)
//    var isBlocked : Boolean,
//
//    @OneToMany(mappedBy="clientAdvert")
//    var response: List<Response>?
//
//) : AdvertEntity(author, title, description, updatedAt, closedAt, place, price)


@Entity
@Table(name="Adverts")
class Advert(
    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    var author: User,

    @Column(nullable = false)//TODO тут должа быть ошибка
    @Enumerated(EnumType.STRING)
    var offerFor : OfferFor,

    @Column(nullable = false, length = 64)
    var title : String,

    @Column(nullable = false, length = 255)
    var description : String,

    @Column(nullable = true)
    var updatedAt : LocalDateTime,

    @Column(nullable = true)
    var closedAt : LocalDateTime?,

    /* Возможно, переделать на хранение геоданных как-то так
    https://medium.datadriveninvestor.com/getting-started-building-location-based-gis-rest-apis-with-java-ca28a8869af3
     */
    @Column(nullable = false, length = 128)
    var place : String,

    @Column(nullable = false)
    var price : Int,

//    @Column(nullable = true)
//    @Enumerated(EnumType.STRING)
//    var closedReason : Reason,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status : AdvStatus,

    @OneToMany(mappedBy="advert")
    var response: List<Response>?

) : AbstractEntity()

