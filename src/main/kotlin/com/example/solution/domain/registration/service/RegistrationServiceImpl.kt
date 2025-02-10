package com.example.solution.domain.registration.service

import com.example.solution.exeption.ExistEntityException
import com.example.solution.domain.registration.dto.RegistrationResponse
import com.example.solution.domain.registration.entity.Registration
import com.example.solution.domain.registration.repository.RegistrationRepository
import org.springframework.stereotype.Service

@Service
class RegistrationServiceImpl(
    private val registrationRepository: RegistrationRepository
):RegistrationService{

    // checkParam? true= 번호가 존재하면 예외 / false= 번호가 존재하지 않으면 예외
    override fun checkNumber(number: String, checkParam: Boolean) {
        val exists = registrationRepository.existsByNumber(number)

        if (checkParam && exists) {
            throw IllegalArgumentException("이미 발급된 번호입니다.")
        } else if (!checkParam && !exists) {
            throw IllegalArgumentException("발급되지 않은 번호입니다.")
        }
    }

    override fun getRegistrationNumber(number: String): RegistrationResponse {
        return registrationRepository.findByNumberWithLotto(number)
            ?: throw ExistEntityException("엔티티가 존재하지 않습니다.")
    }

    override fun setRegistrationNumber(lottoId:Long,number: String){
        registrationRepository.save(Registration(number,lottoId))
    }
}