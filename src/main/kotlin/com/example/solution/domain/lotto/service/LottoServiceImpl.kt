package com.example.solution.domain.lotto.service

import com.example.solution.auth.twilio.service.TwilioService
import com.example.solution.domain.lotto.dto.LottoResultResponse
import com.example.solution.domain.lotto.dto.LottoUseResponse
import com.example.solution.domain.lotto.entity.Lotto
import com.example.solution.domain.lotto.repository.LottoRepository
import com.example.solution.domain.registration.service.RegistrationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.locks.ReentrantLock

@Service
class LottoServiceImpl(
    private val registrationService: RegistrationService,
    private val lottoRepository: LottoRepository,
    private val twilioService: TwilioService
):LottoService {

    companion object {
        private val lock = ReentrantLock()
    }

    override fun generateLotto(phone: String, lottoNumber:String) {
        lottoRepository.saveAll(Lotto.generateLotto(lottoNumber))
        val lotto=getLotto()
        registrationService.setRegistrationNumber(lotto.id!!,phone)
    }

    override fun getLottoResult(phone: String): LottoResultResponse {
        registrationService.checkNumber(phone, checkParam = false)
        val result = registrationService.getRegistrationNumber(phone)
        return LottoResultResponse.convertDto(result)
    }

    private fun getLotto(): Lotto {
        return lottoRepository.findTopByIsUseFalseOrderByIdAsc()
            ?.also {
                it.isUse = true // 로또 사용 처리
            } ?: throw IllegalArgumentException("전체 소진")
    }

    @Transactional
    override fun useLotto(phone: String): LottoUseResponse {
        lock.lock()
        try {
            //1. 유저 핸드폰 번호 조회 있으면 예외
            registrationService.checkNumber(phone)

            // 2. 남은 로또번호 확인 후 발급
            val lotto = getLotto()

            //3. 로또ID 와 유저 핸드폰 번호 저장
            registrationService.setRegistrationNumber(lotto.id!!, phone)

            twilioService.sendSms(phone,"lotto number [${lotto.number}]")

            return LottoUseResponse(lotto.number, phone)
        } catch (e: Exception) {
            throw IllegalArgumentException(e.message)
        } finally {
            lock.unlock()
        }
    }
}