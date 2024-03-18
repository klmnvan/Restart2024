package com.example.restartchempionat2024.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityProfileBinding
import com.example.restartchempionat2024.objects.General
import com.example.restartchempionat2024.objects.PrefManager
import com.example.restartchempionat2024.objects.Requests
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Profile : ActivityCustomTheme() {
    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
        initSwitch()
        initUserData()
    }

    /** Функция, где подставляются значения баланса, фотографии и имени */
    @SuppressLint("SetTextI18n")
    private fun  initUserData() {
        with(binding){
            tBalance.text = General.getBalance(UserData.profile.currentBalance.toString())
            tHello.text = "Hello ${UserData.profile.name}"
            //фото
        }
    }

    /** Функция, где переключается тема */
    private fun initTheme() {
        with(binding){
            switch1.setOnCheckedChangeListener { compoundButton, isChecked ->
                if (isChecked){
                    PrefManager.isLightTheme = false
                    UserData.theme = R.style.DarkMode
                    recreate()
                } else {
                    PrefManager.isLightTheme = true
                    UserData.theme = R.style.LightMode
                    recreate()
                }
            }
        }
    }

    /** Функция, где настраивается свитч, если темная тема - включен, если нет - выключен */
    private fun initSwitch() {
        if (PrefManager.isLightTheme){
            binding.switch1.isChecked = false
            initTheme()
        } else {
            binding.switch1.isChecked = true
            initTheme()
        }
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnWallet.setOnClickListener {
                startActivity(Intent(this@Profile, Wallet::class.java))
                finish()
            }
            btnTrack.setOnClickListener {
                startActivity(Intent(this@Profile, Track::class.java))
                finish()
            }
            btnHome.setOnClickListener {
                startActivity(Intent(this@Profile, Home::class.java))
                finish()
            }
            btnNotif.setOnClickListener {
                startActivity(Intent(this@Profile, Notification::class.java))
                finish()
            }
            btnCard.setOnClickListener {
                startActivity(Intent(this@Profile, AddPaymentMethod::class.java))
                finish()
            }
            btnReport.setOnClickListener {
                startActivity(Intent(this@Profile, SendAPackage::class.java))
                finish()
            }
            btnBalance.setOnClickListener {
                PrefManager.balanceIsOpen = !PrefManager.balanceIsOpen
                initUserData()
            }
            btnLogOut.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    with(binding){
                        try {
                            Toast.makeText(this@Profile, "Выхожу из системы", Toast.LENGTH_SHORT).show()
                            Requests.logOut()
                            runOnUiThread {
                                Toast.makeText(this@Profile, "Успешный выход", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception){
                            Toast.makeText(this@Profile, e.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                startActivity(Intent(this@Profile, LogIn::class.java))
                finish()
            }
        }
    }


}