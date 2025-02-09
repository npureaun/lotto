package com.example.solution.domain.registration.repository

import com.example.solution.domain.registration.dto.RegistrationResponse
import com.example.solution.domain.registration.entity.Registration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RegistrationRepository:JpaRepository<Registration,Long> {
    fun existsByNumber(number: String):Boolean
    fun findRegistrationsByNumber(number: String):Registration


    // number를 기준으로 Registration과 Lotto를 JOIN하고, 필요한 필드를 RegistrationWithLottoDTO로 반환
    @Query("SELECT new com.example.solution.domain.registration.dto.RegistrationResponse" +
        "(r.id, r.number, r.createdAt, l.id, l.number,l.rank) " +
        "FROM Registration r JOIN Lotto l ON r.lottoID = l.id WHERE r.number = :number")
    fun findByNumberWithLotto(@Param("number") number: String): RegistrationResponse?
}