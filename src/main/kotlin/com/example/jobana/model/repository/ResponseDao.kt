package com.example.jobana.model.repository


import com.example.jobana.model.entities.Response
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ResponseDao : CrudRepository<Response, Long>