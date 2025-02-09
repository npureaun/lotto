package com.example.solution.domain.lotto.dto

import com.example.solution.domain.registration.dto.RegistrationResponse

data class LottoResultResponse(
    val lottoId: Long,
    val lottoNumber: String,
    val userNumber:String,
    val rank:Int
){
    companion object{
        fun convertDto(registrationDto: RegistrationResponse):LottoResultResponse{
            return LottoResultResponse(
                lottoId = registrationDto.lottoId,
                lottoNumber = registrationDto.lottoNumber,
                userNumber = registrationDto.userNumber,
                rank = registrationDto.rank
            )
        }
    }
}
