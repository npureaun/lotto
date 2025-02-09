package com.example.solution.domain.lotto.repository

import com.example.solution.domain.lotto.entity.Lotto
import org.springframework.data.jpa.repository.JpaRepository

interface LottoRepository:JpaRepository<Lotto,Long> {
    fun findTopByIsUseFalseOrderByIdAsc(): Lotto?
}