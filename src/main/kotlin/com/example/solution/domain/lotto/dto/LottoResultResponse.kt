package com.example.solution.domain.lotto.dto

import com.example.solution.domain.lotto.entity.Lotto
import com.example.solution.domain.registration.dto.RegistrationResponse

data class LottoResultResponse(
    val lottoId: Long,
    val lottoNumber: String,
    val rank:Int
){
    companion object{
        fun toDto(lotto: Lotto):LottoResultResponse{
            return LottoResultResponse(
                lottoId = lotto.id!!,
                lottoNumber = lotto.number,
                rank = lotto.rank
            )
        }
    }
}
