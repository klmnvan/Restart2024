package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityForgotPasswordBinding
import com.example.restartchempionat2024.databinding.ActivitySignUpBinding
import com.example.restartchempionat2024.objects.PrefManager

class ForgotPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnSendOtp.setOnClickListener {
                startActivity(Intent(this@ForgotPassword, OTPVerification::class.java))
                finish()
            }
            btnSignIn.setOnClickListener {
                startActivity(Intent(this@ForgotPassword, LogIn::class.java))
                finish()
            }
        }
    }

}