package com.example.solution.domain.lotto.service

import com.example.solution.domain.lotto.dto.LottoResultResponse
import com.example.solution.domain.lotto.dto.LottoUseResponse

interface LottoService {
    fun useLotto(phone:String): LottoUseResponse
    fun generateLotto(phone: String, lottoNumber:String)
    fun getLottoResult(phone: String):LottoResultResponse
}