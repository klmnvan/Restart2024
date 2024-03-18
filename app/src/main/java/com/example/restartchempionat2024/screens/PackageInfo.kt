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
import com.example.chempionat2024.models.OrderDestination
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.adapters.AdapterD
import com.example.restartchempionat2024.adapters.AdapterPackage
import com.example.restartchempionat2024.databinding.ActivityPackageInfoBinding
import com.example.restartchempionat2024.models.DestinationDetail
import com.example.restartchempionat2024.objects.General
import com.example.restartchempionat2024.objects.Requests
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp

class PackageInfo : ActivityCustomTheme() {
    lateinit var binding: ActivityPackageInfoBinding
    private val adapterPackage = AdapterPackage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPackageInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initInfo()
        pressingButton()
    }

    /** Функция, где к серверу отправляется запрос на получение заказа */
    private fun initInfo() {
        with(binding){
            Toast.makeText(applicationContext, "Получаю данные о заказе", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val idLastOrder = UserData.lastOrder.id
                    UserData.lastOrder = Requests.selOrder(idLastOrder)
                    UserData.lastOrigDet = Requests.selOrDet(UserData.lastOrder.originId)
                    UserData.lastPackDet = Requests.selPackDet(UserData.lastOrder.packageId)
                    val ordDest = Requests.selOrdDest(idLastOrder)
                    UserData.lastDestDet.clear()
                    ordDest.forEach {
                        UserData.lastDestDet.add(Requests.selDestDet(it.destinationId))
                    }
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Получил", Toast.LENGTH_SHORT).show()
                        parsData()
                        //не финиш!
                    }
                } catch (e:Exception) {
                    Toast.makeText(this@PackageInfo, e.message.toString(), Toast.LENGTH_LONG).show()
                    Log.d("SendAPackage", e.message.toString())
                }
            }
        }
    }

    private fun parsData(){
        with(binding) {
            listDestDetView.layoutManager = GridLayoutManager(this@PackageInfo, 1)
            listDestDetView.adapter = adapterPackage
            UserData.lastDestDet.forEach {
                adapterPackage.addInList(it)
            }
            tCountry.text = UserData.lastOrigDet.stateCountry
            tPhone.text = UserData.lastOrigDet.phone
            tOther.text = UserData.lastOrigDet.others
            tPackItems.text = UserData.lastPackDet.packageItems
            tWeight.text = UserData.lastPackDet.weight.toString()
            tWorth.text = UserData.lastPackDet.worth.toString()
            tTrack.text = UserData.lastOrder.trackingNumber
            //Подсчёт Charges (можно забить на Instant delivery (0,05 баллов всего)

        }
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnBack.setOnClickListener {
                UserData.nulableLastOrder()
                startActivity(Intent(this@PackageInfo, Profile::class.java))
                finish()
            }
            btnMakePayment.setOnClickListener {
                startActivity(Intent(this@PackageInfo, TransactionSuccessful::class.java))
                finish()
            }
            btnEditPack.setOnClickListener {
                finish()
            }
        }
    }

}