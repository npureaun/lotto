package com.example.solution.auth.twilio.dto

data class UserVerifyCheckRequestDto(
    val phone:String,
    val code:String
)
