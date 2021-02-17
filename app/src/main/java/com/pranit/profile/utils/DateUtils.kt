package com.pranit.profile.utils

import java.time.LocalDate
import java.time.Period

fun getAge(year: Int, month: Int, dayOfMonth: Int): Triple<Int, Int, Int> {
    val period = Period.between(
        LocalDate.of(year, month, dayOfMonth),
        LocalDate.now()
    )

    return Triple(period.years, period.months, period.days)
}