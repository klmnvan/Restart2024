package com.example.restartchempionat2024.objects

import com.example.chempionat2024.models.Order
import com.example.chempionat2024.models.OriginDetail
import com.example.chempionat2024.models.PackageDetail
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.models.DestinationDetail
import com.example.restartchempionat2024.models.Profiles

/** UserData -
 * хранилице данных, доступ к которым нужен из нескольких классов в проекте.
 * Создан для удобства обращений к переменным */
object UserData {

    var theme: Int = R.style.LightMode
    var profile: Profiles = Profiles()
    var lastOrder = Order()
    var lastOrigDet: OriginDetail = OriginDetail()
    var lastPackDet: PackageDetail = PackageDetail()
    var lastDestDet: MutableList<DestinationDetail> = ArrayList()
    var lastDestDetIsNotEmpty = false

    fun nulableLastOrder(){
        lastOrder = Order()
        lastOrigDet = OriginDetail()
        lastPackDet = PackageDetail()
        lastDestDet = ArrayList()
        lastDestDetIsNotEmpty = false
    }

}