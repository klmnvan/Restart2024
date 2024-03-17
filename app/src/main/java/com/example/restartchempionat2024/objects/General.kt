package com.example.restartchempionat2024.objects

import android.text.TextUtils
import com.example.restartchempionat2024.objects.General.isEmailValid

/** General -
 * объект, в котором находятся функуии, которые используются сразу из нескольких активностей */
object General {

    /** Функция, которая проверяет соответствие приходящей строки паттерну электронной почты */
    fun String.isEmailValid () : Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }



}