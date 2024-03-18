package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chempionat2024.models.Order
import com.example.chempionat2024.models.OrderDestination
import com.example.chempionat2024.models.OriginDetail
import com.example.chempionat2024.models.PackageDetail
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.adapters.AdapterD
import com.example.restartchempionat2024.databinding.ActivityAddPaymentMethodBinding
import com.example.restartchempionat2024.databinding.ActivitySendApackageBinding
import com.example.restartchempionat2024.models.DestinationDetail
import com.example.restartchempionat2024.objects.General
import com.example.restartchempionat2024.objects.Requests
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp

class SendAPackage : ActivityCustomTheme(), AdapterD.Listener {
    lateinit var binding: ActivitySendApackageBinding
    private val adapterD = AdapterD(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendApackageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
        with(binding){
            listDestDetView.layoutManager = GridLayoutManager(this@SendAPackage, 1)
            listDestDetView.adapter = adapterD
        }
        addEmptyDestination()
    }

    /** Функция, в которой создается пустое поле для пункта доставки */
    private fun addEmptyDestination() {
        UserData.lastDestDet.add(DestinationDetail(0, "", "","",""))
        adapterD.clearList()
        UserData.lastDestDet.forEach {
            adapterD.addDest(it)
        }
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnBack.setOnClickListener {
                UserData.nulableLastOrder()
                startActivity(Intent(this@SendAPackage, Profile::class.java))
                finish()
            }
            btnInstant.setOnClickListener {
                instantOrder()
            }
            btnAddDest.setOnClickListener {
                addEmptyDestination()
            }
        }
    }

    /** Функция, где отправляется запрос на отправку заказа на сервер */
    private fun instantOrder() {
        with(binding){
            if(iAddress.text.isNotEmpty() && iCountry.text.isNotEmpty() &&
                iPhone.text.isNotEmpty() && iPackageItems.text.isNotEmpty() &&
                iWeight.text.isNotEmpty() && iWorth.text.isNotEmpty() && UserData.lastDestDetIsNotEmpty) {

                val address = iAddress.text.toString()
                val country = iCountry.text.toString()
                val phone = iPhone.text.toString()
                val other = iOthers.text.toString()
                val pack = iPackageItems.text.toString()
                val weight = iWeight.text.toString().toFloat()
                val worth = iWorth.text.toString().toFloat()

                UserData.lastOrigDet = UserData.lastOrigDet.copy(
                    address = address,
                    stateCountry = country,
                    phone = phone,
                    others = other
                )
                UserData.lastPackDet = UserData.lastPackDet.copy(
                    weight = weight,
                    worth = worth,
                    packageItems = pack
                )
                //Пункты доставки из адаптеры автоматом парсятся
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        //отправляет upsert и сразу парсим в UserData
                        Toast.makeText(this@SendAPackage, "Отправляю заказ", Toast.LENGTH_LONG).show()
                        UserData.lastOrigDet = UserData.lastOrigDet.copy(
                            id = Requests.setOriginDet(UserData.lastOrigDet).id
                        )
                        UserData.lastPackDet = UserData.lastPackDet.copy(
                            id = Requests.setPackDet(UserData.lastPackDet).id
                        )
                        UserData.lastOrder = UserData.lastOrder.copy(
                            profileId = UserData.profile.id!!,
                            originId = UserData.lastOrigDet.id,
                            packageId = UserData.lastPackDet.id,
                            createdAt = Timestamp(System.currentTimeMillis()).toString(),
                            trackingNumber = General.generateTrackingNumber(UserData.profile.id!!)
                        )
                        UserData.lastOrder = UserData.lastOrder.copy(
                            id = Requests.setOrder(UserData.lastOrder).id
                        )
                        for (i in 0 until UserData.lastDestDet.size){
                            UserData.lastDestDet[i] = UserData.lastDestDet[i].copy(
                                id = Requests.setDestDet(UserData.lastDestDet[i]).id)
                            Requests.setOrderDest(OrderDestination(UserData.lastOrder.id, UserData.lastDestDet[i].id))
                        }
                        Toast.makeText(applicationContext, "Данные отправлены", Toast.LENGTH_LONG).show()
                        runOnUiThread {
                            startActivity(Intent(this@SendAPackage, PackageInfo::class.java))
                            //не финиш!
                        }
                    } catch (e:Exception) {
                        Toast.makeText(this@SendAPackage, e.message.toString(), Toast.LENGTH_LONG).show()
                        Log.d("SendAPackage", e.message.toString())
                    }
                }

            } else {
                Toast.makeText(applicationContext, "Не все обязательные поля заполнены", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun setDestinationDetail(d: DestinationDetail, p: Int) {
        UserData.lastDestDet[p] = d
    }

}