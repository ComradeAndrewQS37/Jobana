package com.example.jobana.controller

import com.example.jobana.model.dto.request.UserRequest
import com.example.jobana.model.dto.response.ApiResponse
import com.example.jobana.model.dto.response.message.DeletedMessage
import com.example.jobana.service.AdvertService
import com.example.jobana.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService,
    val advertService: AdvertService
) {

    @GetMapping("/all")
    fun getAll() = userService.getAll()


    @GetMapping
    fun getById(@RequestParam id: Long) = userService.getById(id)

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun createUser(@RequestBody request: UserRequest) = userService.createUser(request.asEntity())

    @DeleteMapping
    fun deleteUser(@RequestParam id: Long): ResponseEntity<ApiResponse> {
        userService.deleteUser(id)
        return DeletedMessage().asResponse()
    }

    //Выдает все объявления юзера и клиентские, и экспертские(key = byUser)
    @GetMapping("/{user_id}/adverts")
    fun getAdvertsByUser(@PathVariable(value = "user_id") user_id: Long) = advertService.getAdvertsByUser(user_id)

    /*
    @Modifying
    @PutMapping
    fun update(@RequestParam id:Long, @RequestBody request: UserRequest) = service.updateUser(id, request)
     */

}