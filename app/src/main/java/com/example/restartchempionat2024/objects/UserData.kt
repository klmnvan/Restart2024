package com.example.restartchempionat2024.objects

import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.models.Profile

/** UserData -
 * хранилице данных, доступ к которым нужен из нескольких классов в проекте.
 * Создан для удобства обращений к переменным */
object UserData {

    var theme: Int = R.style.LightMode
    var profile: Profile = Profile()



}