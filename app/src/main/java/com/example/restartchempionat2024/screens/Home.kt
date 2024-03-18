package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityHomeBinding
import com.example.restartchempionat2024.objects.PrefManager
import com.example.restartchempionat2024.objects.Requests
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Home : ActivityCustomTheme() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
        initUserData()
    }

    /** Функция, где осуществляется запрос на сервер, получаются баланс, фото и имя профиля */
    private fun initUserData(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                Toast.makeText(applicationContext, "Получаю данные профиля", Toast.LENGTH_SHORT).show()
                UserData.profile = Requests.getProfile(PrefManager.email)
            } catch (e: Exception) {
                Toast.makeText(this@Home, e.message.toString(), Toast.LENGTH_LONG).show()
            }

        }
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnProfile.setOnClickListener {
                startActivity(Intent(this@Home, Profile::class.java))
                finish()
            }
            btnWallet.setOnClickListener {
                startActivity(Intent(this@Home, Wallet::class.java))
                finish()
            }
            btnTrack.setOnClickListener {
                startActivity(Intent(this@Home, Track::class.java))
                finish()
            }
        }
    }
}