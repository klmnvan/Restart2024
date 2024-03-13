package com.example.restartchempionat2024.theme

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.objects.PrefManager
import com.example.restartchempionat2024.objects.UserData

/** ActivityCustomTheme - класс, от которого будут наследоваться в дальнейшем все классы активностей.
 * Необходим для настройки темы */
open class ActivityCustomTheme : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefManager.init(this@ActivityCustomTheme)
        if(!PrefManager.isLightTheme){
            UserData.theme = R.style.DarkMode
        }
        //получаем значение из Shared Pref в UserData
        setTheme(UserData.theme)
    }
}