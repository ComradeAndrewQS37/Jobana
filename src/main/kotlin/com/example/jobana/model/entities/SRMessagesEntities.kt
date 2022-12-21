package com.example.jobana.model.entities

import jakarta.persistence.*

@Entity
@Table(name = "SupportMessages")
class SupportMessages (
    @ManyToOne
        @JoinColumn(name="user_id", nullable = false)
        var user: User,

    @ManyToOne
        @JoinColumn(name="admin_id", nullable = false)
        var admin: Admin,

    @Column(nullable = false, length = 1024)
        var description : String,

    @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        var status : SRMStatus,

    ) : AbstractEntity()


@Entity
@Table(name = "ReportMessages")
class ReportMessages (
    @ManyToOne
        @JoinColumn(name="user_id", nullable = false)
        var user: User,

    @ManyToOne
        @JoinColumn(name="admin_id", nullable = false)
        var admin: Admin,

    @Column(nullable = false, length = 1024)
        var rp_description : String,

    @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        var status : SRMStatus,

        //Здесь должны быть указатели на то, на что жалуются
        //TODO ...

) : AbstractEntity()