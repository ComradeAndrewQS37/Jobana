package com.example.jobana.controller

import com.example.jobana.controller.response.HealthResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/health")
    fun healthCheck() = HealthResponse().asResponse()

}

