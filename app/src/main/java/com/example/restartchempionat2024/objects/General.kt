package com.example.restartchempionat2024.objects

import android.text.TextUtils
import com.example.restartchempionat2024.objects.General.isEmailValid
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.random.Random

/** General -
 * объект, в котором находятся функуии, которые используются сразу из нескольких активностей */
object General {

    /** Функция, которая проверяет соответствие приходящей строки паттерну электронной почты */
    fun String.isEmailValid () : Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    /** Функция, которая проверяет соответствие приходящей строки паттерну электронной почты */
    fun getBalance (balance: String) : String {
        var newStr = ""
        balance.forEach {
            newStr += '*'
        }
        return if (PrefManager.balanceIsOpen) balance
        else newStr
    }

    /** Функция, которая генерирует номер заказа по uuid */
    fun generateTrackingNumber(uuid: String): String {
        val shablon = "R-****-****-****-****"
        val symbols = uuid.replace("-","")
        var track = ""
        shablon.forEach {
            if(it == '*') track += symbols[Random.nextInt(0, symbols.length)]
            else track += it
        }
        return track
    }

    /** Функция, которая преобразует дату в нужный формат */
    fun convertTimestampToCustomFormat(timestamp: String): String {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        return try {
            val offsetDateTime = OffsetDateTime.parse(timestamp, formatter)
            val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
            outputFormatter.format(offsetDateTime)
        } catch (e: DateTimeParseException) {
            "Неверный формат временной метки"
        }
    }



}