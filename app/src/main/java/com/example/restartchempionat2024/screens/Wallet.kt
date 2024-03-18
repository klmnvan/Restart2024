package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityHomeBinding
import com.example.restartchempionat2024.databinding.ActivityWalletBinding
import com.example.restartchempionat2024.theme.ActivityCustomTheme

class Wallet : ActivityCustomTheme() {
    lateinit var binding: ActivityWalletBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnProfile.setOnClickListener {
                startActivity(Intent(this@Wallet, Profile::class.java))
                finish()
            }
            btnTrack.setOnClickListener {
                startActivity(Intent(this@Wallet, Track::class.java))
                finish()
            }
            btnHome.setOnClickListener {
                startActivity(Intent(this@Wallet, Home::class.java))
                finish()
            }
        }
    }
}