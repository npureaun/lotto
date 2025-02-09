package com.example.solution.util.`object`

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil

object EncodeUtils {
    fun getE164FormatPhoneNumber(number: String): String {
        try {
            val phoneNumberUtil = PhoneNumberUtil.getInstance()
            val parsedPhoneNumber = phoneNumberUtil.parse(number, "KR")
            return phoneNumberUtil.format(parsedPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
        } catch (e: NumberParseException) {
            throw RuntimeException(e)
        }
    }
}