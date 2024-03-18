package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityHomeBinding
import com.example.restartchempionat2024.databinding.ActivityTrackBinding
import com.example.restartchempionat2024.theme.ActivityCustomTheme

class Track : ActivityCustomTheme() {
    lateinit var binding: ActivityTrackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnProfile.setOnClickListener {
                startActivity(Intent(this@Track, Profile::class.java))
                finish()
            }
            btnWallet.setOnClickListener {
                startActivity(Intent(this@Track, Wallet::class.java))
                finish()
            }
            btnHome.setOnClickListener {
                startActivity(Intent(this@Track, Home::class.java))
                finish()
            }
        }
    }
}