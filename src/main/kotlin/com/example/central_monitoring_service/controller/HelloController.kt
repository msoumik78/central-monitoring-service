package com.example.central_monitoring_service.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
  @GetMapping("/")
  fun index(@RequestParam("name") name: String) = "Hello, $name!"
}
