package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restartchempionat2024.databinding.ActivityOtpverificationBinding

class OTPVerification : AppCompatActivity() {
    private lateinit var binding: ActivityOtpverificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnResend.setOnClickListener {

            }
            btnResetPass.setOnClickListener {
                startActivity(Intent(this@OTPVerification, NewPassword::class.java))
                finish()
            }
            btnVerify.setOnClickListener {

            }
        }
    }

}