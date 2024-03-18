package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityHomeBinding
import com.example.restartchempionat2024.theme.ActivityCustomTheme

class Home : ActivityCustomTheme() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
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