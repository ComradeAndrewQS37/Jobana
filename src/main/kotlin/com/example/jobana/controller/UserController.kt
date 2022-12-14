package com.example.jobana.controller

import com.example.jobana.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
        val service : UserService
        )
{

    @GetMapping("/all")
    fun getAll() = service.getAll()


    @GetMapping
    fun getById(@RequestParam id:Long) = service.getById(id)

}