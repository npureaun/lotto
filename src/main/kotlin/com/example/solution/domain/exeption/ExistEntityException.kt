package com.example.solution.domain.exeption

data class ExistEntityException(
    val errorMessage: String
) : IllegalStateException() {
    override val message: String = errorMessage
}