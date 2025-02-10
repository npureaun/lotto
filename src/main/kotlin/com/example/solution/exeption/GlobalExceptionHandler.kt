package com.example.solution.exeption

import com.example.solution.exeption.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto(e.message, "400"))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto(e.message, "400"))
    }

    @ExceptionHandler(ExistEntityException::class)
    fun handleExistEntityException(e: ExistEntityException): ResponseEntity<ErrorDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto(e.message, "400"))
    }
}