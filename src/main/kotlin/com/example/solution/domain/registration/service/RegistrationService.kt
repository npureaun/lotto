package com.example.solution.domain.registration.service

import com.example.solution.domain.registration.dto.RegistrationResponse

interface RegistrationService {
    fun checkNumber(number: String, checkParam:Boolean= true)
    fun setRegistrationNumber(lottoId:Long,number: String)
    fun getRegistrationNumber(number: String): RegistrationResponse
}