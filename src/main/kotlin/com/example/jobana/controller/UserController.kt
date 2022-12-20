package com.example.jobana.controller

import com.example.jobana.model.dto.request.UserRegisterDTO
import com.example.jobana.model.dto.response.ApiResponse
import com.example.jobana.model.dto.response.message.DeletedMessage
import com.example.jobana.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val service: UserService
) {

    @GetMapping("/all")
    fun getAll() = service.getAll()


    @GetMapping
    fun getById(@RequestParam id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun createUser(@RequestBody request: UserRegisterDTO) = service.createUser(request.asEntity())


    @DeleteMapping
    fun deleteUser(@RequestParam id: Long): ResponseEntity<ApiResponse> {
        service.deleteUser(id)
        return DeletedMessage().asResponse()
    }

    /*
    @PutMapping
    fun update(@RequestParam id:Long, @RequestBody request: UserRequest) = service.updateUser(id, request)
     */

}