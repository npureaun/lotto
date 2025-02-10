package com.example.solution.domain.registration.service

import com.example.solution.domain.registration.dto.RegistrationResponse

interface RegistrationService {
    fun checkNumber(phone: String, checkParam:Boolean= true)
    fun setRegistrationNumber(lottoId:Long,phone: String)
    fun getRegistrationNumber(phone: String): RegistrationResponse
}