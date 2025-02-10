package com.example.solution.auth.service

import com.example.solution.auth.dto.UserVerifyCheckRequestDto
import com.example.solution.auth.dto.UserVerifyCodeRequestDto
import com.example.solution.util.twilio.TwilioComponent
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val twilioComponent: TwilioComponent
):AuthService {

    override fun twilioVerificationSend(userVerifyCodeRequestDto: UserVerifyCodeRequestDto): String {
        return twilioComponent.verification(userVerifyCodeRequestDto)
    }

    override fun twilioVerificationCheck(userVerifyCheckRequestDto: UserVerifyCheckRequestDto): String {
        return twilioComponent.verificationCheck(userVerifyCheckRequestDto)
    }
}