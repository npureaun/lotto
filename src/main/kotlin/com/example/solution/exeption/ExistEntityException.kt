package com.example.solution.exeption

data class ExistEntityException(
    val errorMessage: String
) : IllegalStateException() {
    override val message: String = errorMessage
}