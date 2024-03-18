package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityHomeBinding
import com.example.restartchempionat2024.databinding.ActivityNotificationBinding

class Notification : AppCompatActivity() {
    lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState))
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnBack.setOnClickListener {
                startActivity(Intent(this@Notification, Profile::class.java))
                finish()
            }
        }
    }
}