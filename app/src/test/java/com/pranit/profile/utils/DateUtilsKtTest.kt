package com.pranit.profile.utils

import android.text.format.DateUtils
import org.junit.Test

import org.junit.Assert.*

class DateUtilsKtTest {

    @Test
    fun test_getAge() {
        val year = 1989
        val month = 4
        val day = 29
        print("Age ${getAge(year, month, day)}")
    }
}