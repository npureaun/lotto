package com.example.solution.domain.lotto.dto

import com.example.solution.domain.registration.dto.RegistrationResponse

data class LottoResultResponse(
    val lottoId: Long,
    val lottoNumber: String,
    val phoneNumber:String,
    val rank:Int
){
    companion object{
        fun convertDto(registrationDto: RegistrationResponse):LottoResultResponse{
            return LottoResultResponse(
                lottoId = registrationDto.lottoId,
                lottoNumber = registrationDto.lottoNumber,
                phoneNumber = registrationDto.phoneNumber,
                rank = registrationDto.rank
            )
        }
    }
}
