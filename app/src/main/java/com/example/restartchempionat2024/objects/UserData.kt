package com.example.restartchempionat2024.objects

import com.example.chempionat2024.models.Order
import com.example.chempionat2024.models.OriginDetail
import com.example.chempionat2024.models.PackageDetail
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.models.DestinationDetail
import com.example.restartchempionat2024.models.Profile

/** UserData -
 * хранилице данных, доступ к которым нужен из нескольких классов в проекте.
 * Создан для удобства обращений к переменным */
object UserData {

    var theme: Int = R.style.LightMode
    var profile: Profile = Profile()
    var lastOrder = Order(0, "", 0, 0, "", 0, "",
        0, true, "")
    var lastOrigDet: OriginDetail = OriginDetail(0, "", "", "", "")
    var lastPackDet: PackageDetail = PackageDetail(0, 0F, 0F, "")
    var lastDestDet: MutableList<DestinationDetail> = ArrayList()

    fun nulableLastOrder(){
        lastOrder = Order(0, "", 0, 0, "",
            0, "", 0, true, "")
        lastOrigDet = OriginDetail(0, "", "", "", "")
        lastPackDet = PackageDetail(0, 0F, 0F, "")
        lastDestDet = ArrayList()
    }


}