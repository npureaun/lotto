package com.example.solution.auth.twilio.controller

import com.example.solution.auth.twilio.dto.UserVerifyCheckRequestDto
import com.example.solution.auth.twilio.dto.UserVerifyCodeRequestDto
import com.example.solution.auth.twilio.service.TwilioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TwilioController(
    private val twilioService: TwilioService
) {
    @PostMapping("/user/authenticate/code")
    fun getUserAuthenticateCode(@RequestBody userVerifyCodeRequestDto: UserVerifyCodeRequestDto): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body(twilioService.verification(userVerifyCodeRequestDto))
    }

    @PostMapping("/user/authenticate/check")
    fun verifyUserAuthenticateCode(@RequestBody userVerifyCheckRequestDto: UserVerifyCheckRequestDto)
    : ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body(twilioService.verificationCheck(userVerifyCheckRequestDto))
    }
}