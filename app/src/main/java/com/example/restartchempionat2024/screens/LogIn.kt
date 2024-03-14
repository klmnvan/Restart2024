package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.restartchempionat2024.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnLogIn.setOnClickListener {
                startActivity(Intent(this@LogIn, Holder::class.java))
                finish()
            }
            btnSignUp.setOnClickListener {
                startActivity(Intent(this@LogIn, SignUp::class.java))
                finish()
            }
            btnForgotPass.setOnClickListener {
                startActivity(Intent(this@LogIn, ForgotPassword::class.java))
                finish()
            }
        }
    }
}