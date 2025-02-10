package com.example.solution.util.twilio

import com.example.solution.auth.dto.UserVerifyCheckRequestDto
import com.example.solution.auth.dto.UserVerifyCodeRequestDto

interface TwilioComponent {
    fun verification(userVerifyCodeRequestDto: UserVerifyCodeRequestDto): String
    fun verificationCheck(userVerifyCheckRequestDto: UserVerifyCheckRequestDto): String
    fun sendSms(phone: String, message:String)
}