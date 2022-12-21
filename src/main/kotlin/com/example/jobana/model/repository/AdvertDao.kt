package com.example.jobana.model.repository

import com.example.jobana.model.entity.Advert
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AdvertDao : CrudRepository<Advert, Long>{

    fun findAdvertByAuthor_Id(id: Long): Iterable<Advert>

}