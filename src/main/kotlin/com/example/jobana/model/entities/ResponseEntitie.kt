package com.example.jobana.model.entities

import com.example.jobana.model.entity.Advert
import jakarta.persistence.*

@Entity
@Table(name="Responses")
class Response (
    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    var isAccepted : Boolean,

    @ManyToOne
    @JoinColumn(name="advert_id")
    val advert : Advert,

    ) : AbstractEntity()