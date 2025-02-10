package com.example.solution.auth.twilio.service

import com.example.solution.util.`object`.EncodeUtils
import com.example.solution.config.TwilioConfig
import com.example.solution.auth.twilio.dto.UserVerifyCheckRequestDto
import com.example.solution.auth.twilio.dto.UserVerifyCodeRequestDto
import com.twilio.rest.api.v2010.account.Message
import com.twilio.rest.verify.v2.service.Verification
import com.twilio.rest.verify.v2.service.VerificationCheck
import com.twilio.type.Client
import com.twilio.type.PhoneNumber
import io.jsonwebtoken.io.IOException
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Service
class TwilioServiceImpl(
    private val twilioConfig: TwilioConfig
): TwilioService {
    // 인증 번호 요청
    override fun verification(userVerifyCodeRequestDto: UserVerifyCodeRequestDto): String {
        val e164FormatPhoneNumber: String = EncodeUtils.getE164FormatPhoneNumber(userVerifyCodeRequestDto.phone)

        val verification: Verification = Verification.creator(
            twilioConfig.getServiceSid(),
            e164FormatPhoneNumber,
            "sms"
        ).create()

        println(verification.status)

        return "인증 번호 요청 성공"
    }

    // 인증 번호 검증
    override fun verificationCheck(userVerifyCheckRequestDto: UserVerifyCheckRequestDto): String {
        val e164FormatPhoneNumber: String = EncodeUtils.getE164FormatPhoneNumber(userVerifyCheckRequestDto.phone)
        try {
            val verificationCheck = VerificationCheck.creator(
                twilioConfig.getServiceSid()
            )
                .setTo(e164FormatPhoneNumber)
                .setCode(userVerifyCheckRequestDto.code)
                .create()
            check(verificationCheck.valid)
        } catch (e: IOException) {
            throw IOException("발송 실패")
        }catch (e: Exception) {
            throw IllegalStateException("인증 실패")
        }
        return "인증 번호 검증 성공"
    }

    override fun sendSms(phone: String, message: String) {
        val e164FormatPhoneNumber = PhoneNumber(EncodeUtils.getE164FormatPhoneNumber(phone))
        try {
            val sendMessage = Message.creator(
                e164FormatPhoneNumber,
                twilioConfig.getFromPhone(),
                message
            ).create()

            println(sendMessage.body)
        } catch (e: Exception) {
            throw IOException(e.message)
        }
    }
}