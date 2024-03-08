package com.example.restartchempionat2024.objects

import android.content.Context
import android.content.SharedPreferences

/** PrefManager - объект, в котором находятся переменные типа SharedPreferences.
 * Удобно обращатся из любого класса проекта */
object PrefManager {

    lateinit var spActSystem: SharedPreferences
    lateinit var spProfiles: SharedPreferences
    lateinit var spOrders: SharedPreferences

    //инициализировать Shared Preferences в конкретном контексте (классе активности, например)
    fun init(context: Context) {
        spActSystem = context.getSharedPreferences("Act", Context.MODE_PRIVATE)
        spProfiles = context.getSharedPreferences("Profiles", Context.MODE_PRIVATE)
        spOrders = context.getSharedPreferences("Orders", Context.MODE_PRIVATE)
    }

    var indAct: Int
        get() = spActSystem.getInt("indAct", 0)
        set(value) = spActSystem.edit().putInt("indAct", value).apply()

    var email: String
        get() = spProfiles.getString("email", "")!!
        set(value) = spProfiles.edit().putString("email", value).apply()

    var password: String
        get() = spProfiles.getString("password", "")!!
        set(value) = spProfiles.edit().putString("password", value).apply()

    var isLightTheme: Boolean
        get() = spProfiles.getBoolean("password", true)
        set(value) = spProfiles.edit().putBoolean("password", value).apply()

}