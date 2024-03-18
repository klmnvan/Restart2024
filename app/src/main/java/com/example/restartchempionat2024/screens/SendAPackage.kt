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
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.adapters.AdapterD
import com.example.restartchempionat2024.databinding.ActivityAddPaymentMethodBinding
import com.example.restartchempionat2024.databinding.ActivitySendApackageBinding
import com.example.restartchempionat2024.models.DestinationDetail
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SendAPackage : ActivityCustomTheme() {
    lateinit var binding: ActivitySendApackageBinding
    private val adapterD = AdapterD()

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
        adapterD.addDest(DestinationDetail(0, "", "","",""))
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnBack.setOnClickListener {
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
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    runOnUiThread {
                        startActivity(Intent(this@SendAPackage, Home::class.java))
                        //не финиш!
                    }
                } catch (e:Exception) {
                    Toast.makeText(this@SendAPackage, e.message.toString(), Toast.LENGTH_LONG).show()
                    Log.d("sendRequest ${this@SendAPackage}", e.message.toString())
                }
            }
        }
    }

}