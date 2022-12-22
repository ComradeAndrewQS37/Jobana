package com.example.jobana.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "Admin")
class Admin (

    @OneToMany(mappedBy="admin")
    var supportMessages: List<SupportMessages>,

    @OneToMany(mappedBy="admin")
    var reportMessages: List<ReportMessages>,

) : AbstractEntity()