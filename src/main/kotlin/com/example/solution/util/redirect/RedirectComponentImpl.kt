package com.example.solution.util.redirect

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime

@Component
class RedirectComponentImpl:RedirectComponent {

    override fun getFirstVisitCookie(cookies: Array<Cookie>?): Cookie? {
        return cookies?.find { it.name == "visit" }
    }

    override fun setFirstVisitCookie(response: HttpServletResponse) {
        val cookie = Cookie("visit", "false").apply {
            // 자정까지 유효하도록 설정
            val now = LocalDateTime.now()
            val nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay() // 자정
            val maxAge = Duration.between(now, nextMidnight).seconds.toInt()
            this.maxAge = maxAge
            this.path = "/" // 전체 경로에 대해 유효
        }
        response.addCookie(cookie)
    }

    override fun redirectToPage(location:String): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.FOUND)
            .header("Location", location) // 결과 페이지 경로
            .build()
    }

    override fun deleteFirstVisitCookie(response: HttpServletResponse) {
        val cookie = Cookie("visit", "").apply {
            this.maxAge=0
            this.path="/"
        }

        // 쿠키를 응답에 추가
        response.addCookie(cookie)
    }
}
