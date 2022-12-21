package com.example.jobana.model.entities

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime


@MappedSuperclass
abstract class AbstractEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

}