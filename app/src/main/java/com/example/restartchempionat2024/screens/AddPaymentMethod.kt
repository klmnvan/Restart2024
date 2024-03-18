package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityAddPaymentMethodBinding
import com.example.restartchempionat2024.databinding.ActivityHomeBinding
import com.example.restartchempionat2024.theme.ActivityCustomTheme

class AddPaymentMethod : ActivityCustomTheme() {
    lateinit var binding: ActivityAddPaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnBack.setOnClickListener {
                startActivity(Intent(this@AddPaymentMethod, Profile::class.java))
                finish()
            }
        }
    }
}