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
import com.example.restartchempionat2024.databinding.ActivityNewPasswordBinding
import com.example.restartchempionat2024.objects.General.isEmailValid
import com.example.restartchempionat2024.objects.Requests
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPassword : ActivityCustomTheme() {
    private lateinit var binding: ActivityNewPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
        checkInputFields()
    }

    /** Функция, где отслеживаются изменения полей ввода */
    private fun checkInputFields() {
        with(binding) {
            inptPassword.addTextChangedListener {
                setStyles()
            }
            inptPassword2.addTextChangedListener {
                setStyles()
            }
        }
    }

    /** Функция, где в зависимости от заполнения полей красится кнопка в соответствующий цвет */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setStyles() {
        with(binding) {
            if (inptPassword.text!!.isNotEmpty() &&
                inptPassword2.text!!.isNotEmpty()) {
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
        }
    }

    /** Функция, где отправляется и обрабатывает запрос на сервер */
    private fun sendRequest() {
        with(binding){
            val pass = inptPassword.text.toString()
            val pass2 = inptPassword2.text.toString()
            if(pass == pass2){
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        Toast.makeText(this@NewPassword, "Отправляю запрос...", Toast.LENGTH_LONG).show()
                        Requests.updatePassword(pass)
                        Log.d("auth", Requests.getCurrentSession())
                        runOnUiThread {
                            startActivity(Intent(this@NewPassword, LogIn::class.java))
                            finish()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this@NewPassword, e.message.toString(), Toast.LENGTH_LONG).show()
                        Log.d("sendRequest + $this", e.message.toString())
                    }
                }
            } else {
                Toast.makeText(this@NewPassword, "Пароли не совпадают", Toast.LENGTH_LONG).show()
            }
        }

    }


}