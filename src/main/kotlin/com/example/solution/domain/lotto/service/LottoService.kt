package com.example.solution.domain.lotto.service

import com.example.solution.domain.lotto.dto.LottoResultResponse
import com.example.solution.domain.lotto.dto.LottoUseResponse

interface LottoService {
    fun useLotto(number:String): LottoUseResponse
    fun generateLotto(number: String, lottoNumber:String)
    fun getLottoResult(number: String):LottoResultResponse
}