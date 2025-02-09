package com.example.solution.util.redirect

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity

interface RedirectComponent {
    fun getFirstVisitCookie(cookies: Array<Cookie>?): Cookie?
    fun setFirstVisitCookie(response: HttpServletResponse)
    fun redirectToPage(location:String): ResponseEntity<String>
    fun deleteFirstVisitCookie(response: HttpServletResponse)
}