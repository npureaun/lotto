package com.example.solution.auth.controller

import com.example.solution.auth.dto.UserVerifyCheckRequestDto
import com.example.solution.auth.dto.UserVerifyCodeRequestDto
import com.example.solution.util.twilio.TwilioComponent
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val twilioComponent: TwilioComponent
) {
    @PostMapping("/user/auth/sms/code")
    fun getUserAuthenticateCode(@RequestBody userVerifyCodeRequestDto: UserVerifyCodeRequestDto): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body(twilioComponent.verification(userVerifyCodeRequestDto))
    }

    @PostMapping("/user/auth/sms/check")
    fun verifyUserAuthenticateCode(@RequestBody userVerifyCheckRequestDto: UserVerifyCheckRequestDto)
    : ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.OK).body(twilioComponent.verificationCheck(userVerifyCheckRequestDto))
    }
}