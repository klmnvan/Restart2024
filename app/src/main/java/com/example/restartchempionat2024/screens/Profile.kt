package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityHomeBinding
import com.example.restartchempionat2024.databinding.ActivityProfileBinding
import com.example.restartchempionat2024.theme.ActivityCustomTheme

class Profile : ActivityCustomTheme() {
    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
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
        }
    }
}