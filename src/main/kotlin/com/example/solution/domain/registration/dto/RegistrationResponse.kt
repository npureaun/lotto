package com.example.solution.domain.registration.dto


data class RegistrationResponse(
    val registrationId: Long?,
    val phoneNumber: String,
    val createdAt: String,
    val lottoId: Long,
    val lottoNumber: String,
    val rank:Int
)
