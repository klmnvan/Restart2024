package com.example.restartchempionat2024.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityLogInBinding
import com.example.restartchempionat2024.objects.General.isEmailValid
import com.example.restartchempionat2024.objects.PrefManager
import com.example.restartchempionat2024.objects.Requests
import com.example.restartchempionat2024.objects.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(PrefManager.password != ""){
            binding.inptPassword.setText(PrefManager.password)
        }
        pressingButton()
        checkInputFields()
    }

    /** Функция, где отслеживаются изменения полей ввода */
    private fun checkInputFields() {
        with(binding) {
            inptEmail.addTextChangedListener {
                setStyles()
            }
            inptPassword.addTextChangedListener {
                setStyles()
            }
        }
    }

    /** Функция, где в зависимости от заполнения полей красится кнопка в соответствующий цвет */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setStyles() {
        with(binding) {
            if (inptEmail.text.isNotEmpty() && inptPassword.text!!.isNotEmpty()) {
                btnLogIn.background = getDrawable(R.drawable.blue_5r)
            } else {
                btnLogIn.background = getDrawable(R.drawable.gray_5r)
            }
        }

    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnLogIn.setOnClickListener {
                sendRequest()
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

    /** Функция, где отправляется и обрабатывает запрос на сервер */
    private fun sendRequest() {
        with(binding){
            val email = inptEmail.text.toString()
            val password = inptPassword.text.toString()
            if(email.isEmailValid()){
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        Toast.makeText(this@LogIn, "Отправляю запрос", Toast.LENGTH_SHORT).show()
                        Requests.signIn(email, password)
                        runOnUiThread {
                            if(checkbox.isChecked) PrefManager.password = password
                            PrefManager.email = email
                            PrefManager.passwordSHA512 = getSHA512(password)
                            PrefManager.indAct = 2
                            startActivity(Intent(this@LogIn, Home::class.java))
                            finish()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@LogIn, e.message.toString(), Toast.LENGTH_LONG).show()
                        Log.d("sendRequest + ${this@LogIn}", e.message.toString())
                    }
                }
            } else {
                Toast.makeText(this@LogIn, "email не валиден", Toast.LENGTH_LONG).show()
            }
        }
    }

    /** Получить захэшированную SHA - 512 строку  */
    fun getSHA512(input: String):String {
        return try {
            val md: MessageDigest = MessageDigest.getInstance("SHA-512")
            val messageDigest = md.digest(input.toByteArray())
            val newInput = BigInteger(1, messageDigest)
            var hashtext = newInput.toString(16)
            while (hashtext.length < 128) {
                hashtext = "0$hashtext"
            }
            hashtext
        } catch (e: NoSuchAlgorithmException) {
            return e.message.toString()
        }
    }
}