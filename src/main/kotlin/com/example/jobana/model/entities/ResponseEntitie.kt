package com.example.jobana.model.entities

import com.example.jobana.model.entity.ClientsAdverts
import com.example.jobana.model.entity.ExpertsAdverts
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
    @JoinColumn(name="expertAdvert_id")
    val expertAdvert : ExpertsAdverts,

    //TODO как делать етот .... constraint

    @ManyToOne
    @JoinColumn(name="clientAdvert_id")
    val clientAdvert : ClientsAdverts,

    ) : AbstractEntity()