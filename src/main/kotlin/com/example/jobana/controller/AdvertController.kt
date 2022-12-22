package com.example.jobana.controller

import com.example.jobana.model.dto.request.AdvertRequest
import com.example.jobana.model.dto.response.asData
import com.example.jobana.service.AdvertService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/adverts")
class AdvertController(
    val service : AdvertService
){

    //Выдает объявление, по его индексу
    @GetMapping
    fun getAdvertById(@RequestParam advert_id: Long) =
        service.getAdvertById(advert_id).asData()


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun createAdvert(@RequestBody request: AdvertRequest) =
        service.createAdvert(request.asPreEntity()).asData()

    //Удаление
    //Закрытие
            //Изменение
}