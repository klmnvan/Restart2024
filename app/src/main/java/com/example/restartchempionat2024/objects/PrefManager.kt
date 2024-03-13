package com.example.restartchempionat2024.objects

import android.content.Context
import android.content.SharedPreferences

/** PrefManager - объект, в котором находятся переменные типа SharedPreferences.
 * Удобно обращатся из любого класса проекта */
object PrefManager {

    private lateinit var spActSystem: SharedPreferences
    private lateinit var spProfiles: SharedPreferences
    private lateinit var spOrders: SharedPreferences

    //инициализировать Shared Preferences в конкретном контексте (классе активности, например)
    fun init(context: Context) {
        spActSystem = context.getSharedPreferences("Act", Context.MODE_PRIVATE)
        spProfiles = context.getSharedPreferences("Profiles", Context.MODE_PRIVATE)
        spOrders = context.getSharedPreferences("Orders", Context.MODE_PRIVATE)
    }

    var indAct: Int
        get() = spActSystem.getInt("indAct", 0)
        set(value) = spActSystem.edit().putInt("indAct", value).apply()

    var queueString: String
        get() = spActSystem.getString("queueStr", "")!!
        set(value) = spActSystem.edit().putString("queueStr", value).apply()

    var queueIsCreate: Boolean
        get() = spActSystem.getBoolean("queueBool", false)
        set(value) = spActSystem.edit().putBoolean("queueBool", value).apply()

    var isLightTheme: Boolean
        get() = spProfiles.getBoolean("password", true)
        set(value) = spProfiles.edit().putBoolean("password", value).apply()

}