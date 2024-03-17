package com.example.restartchempionat2024.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityForgotPasswordBinding
import com.example.restartchempionat2024.databinding.ActivitySignUpBinding
import com.example.restartchempionat2024.objects.General.isEmailValid
import com.example.restartchempionat2024.objects.PrefManager
import com.example.restartchempionat2024.objects.Requests
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPassword : ActivityCustomTheme() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
        checkInputFields()
    }

    /** Функция, где отслеживаются изменения полей ввода */
    private fun checkInputFields() {
        with(binding) {
            inptEmail.addTextChangedListener {
                setStyles()
            }
        }
    }

    /** Функция, где в зависимости от заполнения полей красится кнопка в соответствующий цвет */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setStyles() {
        with(binding) {
            if (inptEmail.text.isNotEmpty()) {
                btnSendOtp.background = getDrawable(R.drawable.blue_5r)
            } else {
                btnSendOtp.background = getDrawable(R.drawable.gray_5r)
            }
        }

    }


    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnSendOtp.setOnClickListener {
                sendRequest()
            }
            btnSignIn.setOnClickListener {
                startActivity(Intent(this@ForgotPassword, LogIn::class.java))
                finish()
            }
        }
    }

    /** Функция, где отправляется и обрабатывает запрос на сервер */
    private fun sendRequest() {
        with(binding){
            val email = inptEmail.text.toString()
            if(email.isEmailValid()){

                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        Toast.makeText(this@ForgotPassword, "Отправляю запрос", Toast.LENGTH_SHORT).show()
                        Requests.sendOtp(email)
                        UserData.profile = UserData.profile.copy(email = email)
                        runOnUiThread {
                            startActivity(Intent(this@ForgotPassword, OTPVerification::class.java))
                            finish()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@ForgotPassword, e.message.toString(), Toast.LENGTH_LONG).show()
                        Log.d("sendRequest + ${this@ForgotPassword}", e.message.toString())
                    }
                }
            } else {
                Toast.makeText(this@ForgotPassword, "email не валиден", Toast.LENGTH_LONG).show()
            }
        }
    }

}