package com.example.solution.auth.dto

data class UserVerifyCheckRequestDto(
    val phone:String,
    val code:String
)
