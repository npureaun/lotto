package com.example.solution.config

import com.twilio.Twilio
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class TwilioConfig {
    @Value("\${twilio.account-sid}")
    private val accountSid: String? = null

    @Value("\${twilio.auth-token}")
    private val authToken: String? = null

    @Value("\${twilio.service-sid}")
    private val serviceSid: String? = null

    @PostConstruct
    fun init() {
        Twilio.init(accountSid, authToken)
    }

    fun getServiceSid():String{
        return serviceSid!!
    }
}