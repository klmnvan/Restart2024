package com.example.restartchempionat2024.objects

import android.text.TextUtils
import com.example.restartchempionat2024.objects.General.isEmailValid
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



}