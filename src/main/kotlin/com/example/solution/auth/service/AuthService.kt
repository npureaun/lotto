package com.example.solution.auth.service

import com.example.solution.auth.dto.UserVerifyCheckRequestDto
import com.example.solution.auth.dto.UserVerifyCodeRequestDto

interface AuthService {
    fun twilioVerificationSend(userVerifyCodeRequestDto: UserVerifyCodeRequestDto): String
    fun twilioVerificationCheck(userVerifyCheckRequestDto: UserVerifyCheckRequestDto): String
}