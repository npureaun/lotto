package com.example.solution.auth.twilio.service

import com.example.solution.auth.twilio.dto.UserVerifyCheckRequestDto
import com.example.solution.auth.twilio.dto.UserVerifyCodeRequestDto

interface TwilioService {
    fun verification(userVerifyCodeRequestDto: UserVerifyCodeRequestDto): String
    fun verificationCheck(userVerifyCheckRequestDto: UserVerifyCheckRequestDto): String
    fun sendSms(phone: String, message:String)
}