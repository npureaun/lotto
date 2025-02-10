package com.example.solution.domain.lotto.controller
import com.example.solution.domain.lotto.dto.LottoResultResponse
import com.example.solution.domain.lotto.dto.LottoUseResponse
import com.example.solution.domain.lotto.service.LottoService
import com.example.solution.util.`object`.EventPeriodUtils
import com.example.solution.util.`object`.ResultPeriodUtils
import com.example.solution.util.redirect.RedirectComponent
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1")
@RestController
class LottoController(
    private val lottoService:LottoService,
    private val redirectComponent: RedirectComponent
) {

    private fun isFirstVisit(request: HttpServletRequest,response: HttpServletResponse):Boolean {
        return if (redirectComponent.getFirstVisitCookie(request.cookies) == null) {
            redirectComponent.setFirstVisitCookie(response)
            true
        } else false
    }

    @GetMapping("/delete cookie")
    fun deleteFirstVisitCookie(response: HttpServletResponse):ResponseEntity<String> {
        redirectComponent.deleteFirstVisitCookie(response)
        return ResponseEntity.status(HttpStatus.OK).body("delete")
    }

    @GetMapping
    fun home(request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<String> {
        // 이벤트 기간 체크
        if (isFirstVisit(request, response)) {
            if (EventPeriodUtils.isEventPeriod()) {
                return redirectComponent.redirectToPage("/api/v1/event page")
            } else if (ResultPeriodUtils.isResultPeriod()) {
                return redirectComponent.redirectToPage("/api/v1/result page")
            }
        }
        return ResponseEntity.ok().body("home")
    }

    @GetMapping("/event page")
    fun eventPage():ResponseEntity<String> {
        return  ResponseEntity.ok().body("event")
    }

    @GetMapping("/result page")
    fun resultPage():ResponseEntity<String> {
       return ResponseEntity.ok().body("result")
    }

    @PostMapping("/use")
    fun registration(@RequestParam phone:String)
    :ResponseEntity<LottoUseResponse>{
        return ResponseEntity.status(HttpStatus.OK)
            .body(lottoService.useLotto(phone))
    }

    @GetMapping("/result")
    fun getLottoResult(@RequestParam lottoNumber: String):ResponseEntity<LottoResultResponse>{
        return ResponseEntity.ok().body(lottoService.getLottoResult(lottoNumber))
    }

    @PostMapping("/generate")
    fun generateLotto(@RequestParam phone: String,@RequestParam lottoNumber:String):ResponseEntity<Unit>{
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(lottoService.generateLotto(phone,lottoNumber))
    }
}