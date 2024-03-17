package com.example.restartchempionat2024.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityOtpverificationBinding
import com.example.restartchempionat2024.objects.Requests
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OTPVerification : ActivityCustomTheme() {
    private lateinit var binding: ActivityOtpverificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
        checkInputFields()
        startTimer()
    }

    /** Функция, где запускается таймер на 60 секунд */
    private fun startTimer() {
        binding.btnResend.visibility = View.GONE
        binding.tResend.visibility = View.VISIBLE
        object : CountDownTimer(60000, 1000){
            override fun onTick(p0: Long) {
                binding.tResend.text = "resend after ${p0/1000}"
            }

            override fun onFinish() {
                binding.btnResend.visibility = View.VISIBLE
                binding.tResend.visibility = View.GONE
                binding.btnResend.setOnClickListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        try {
                            Toast.makeText(this@OTPVerification, "Отправляю код", Toast.LENGTH_SHORT).show()
                            Requests.sendOtp(UserData.profile.email!!)
                            runOnUiThread {
                                startTimer()
                            }
                        } catch (e: Exception) {
                            Toast.makeText(this@OTPVerification, e.message.toString(), Toast.LENGTH_LONG).show()
                            Log.d("sendRequest + ${this@OTPVerification}", e.message.toString())
                        }
                    }
                }
            }

        }.start()
    }

    /** Функция, где отслеживаются изменения полей ввода */
    private fun checkInputFields() {
        with(binding) {
            tN1.addTextChangedListener {
                setStyles()
                setStyleInput()
                tN2.requestFocus()
            }
            tN2.addTextChangedListener {
                setStyles()
                setStyleInput()
                tN3.requestFocus()
            }
            tN3.addTextChangedListener {
                setStyles()
                setStyleInput()
                tN4.requestFocus()
            }
            tN4.addTextChangedListener {
                setStyles()
                setStyleInput()
                tN5.requestFocus()
            }
            tN5.addTextChangedListener {
                setStyles()
                setStyleInput()
                tN6.requestFocus()
            }
            tN6.addTextChangedListener {
                setStyles()
                setStyleInput()
            }
        }
    }

    /** Функция, где в зависимости от заполнения полей красится кнопка в соответствующий цвет */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setStyles() {
        with(binding) {
            if (tN1.text.isNotEmpty() && tN2.text.isNotEmpty() &&
                tN3.text.isNotEmpty() && tN4.text.isNotEmpty() &&
                tN5.text.isNotEmpty() && tN6.text.isNotEmpty()) {
                btnVerify.background = getDrawable(R.drawable.blue_5r)
                btnVerify.setOnClickListener {
                    sendRequest()
                }
            } else {
                btnVerify.background = getDrawable(R.drawable.gray_5r)
            }
        }

    }

    /** Функция, где отправляется и обрабатывает запрос на сервер */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun sendRequest() {
        with(binding){
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val code = "${tN1.text}${tN2.text}${tN3.text}${tN4.text}${tN5.text}${tN6.text}"
                    Toast.makeText(this@OTPVerification, "Проверяю код...", Toast.LENGTH_SHORT).show()
                    Requests.verifyCode(UserData.profile.email!!, code)
                    runOnUiThread {
                        Toast.makeText(this@OTPVerification, "Код верный", Toast.LENGTH_SHORT).show()
                        btnResetPass.background = getDrawable(R.drawable.blue_5r)
                        btnResetPass.setOnClickListener {
                            timerBeforeTransition()
                        }
                    }
                } catch (e: Exception) {
                    setStyleError()
                    Toast.makeText(this@OTPVerification, e.message.toString(), Toast.LENGTH_LONG).show()
                    Log.d("sendRequest + $this", e.message.toString())
                }
            }
        }
    }

    /** Функция, где запускается таймер на 60 секунд до перехода на другую активность */
    private fun timerBeforeTransition() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                Toast.makeText(this@OTPVerification, "Сбрасываю пароль", Toast.LENGTH_SHORT).show()
                Requests.resetPassword(UserData.profile.email!!)
                runOnUiThread {
                    Toast.makeText(this@OTPVerification, "60 секунд до перехода", Toast.LENGTH_SHORT).show()
                    object : CountDownTimer(60000, 1000){
                        override fun onTick(p0: Long) { }

                        override fun onFinish() {
                            startActivity(Intent(this@OTPVerification, NewPassword::class.java))
                            finish()
                        }

                    }.start()
                }
            } catch (e: Exception){
                Toast.makeText(this@OTPVerification, e.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("sendRequest + $this", e.message.toString())
            }
        }

    }

    /** Функция, которая красит поля ввода в синий цвет, если они заполнены */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setStyleInput(){
        with(binding){
            if(tN1.text.isNotEmpty()) tN1.background = getDrawable(R.drawable.blue_rectangle)
            if(tN2.text.isNotEmpty()) tN2.background = getDrawable(R.drawable.blue_rectangle)
            if(tN3.text.isNotEmpty()) tN3.background = getDrawable(R.drawable.blue_rectangle)
            if(tN4.text.isNotEmpty()) tN4.background = getDrawable(R.drawable.blue_rectangle)
            if(tN5.text.isNotEmpty()) tN5.background = getDrawable(R.drawable.blue_rectangle)
            if(tN6.text.isNotEmpty()) tN6.background = getDrawable(R.drawable.blue_rectangle)
        }
    }

    /** Функция, которая красит поля ввода в красный цвет */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setStyleError(){
        with(binding){
            tN1.text.clear()
            tN2.text.clear()
            tN3.text.clear()
            tN4.text.clear()
            tN5.text.clear()
            tN6.text.clear()
            tN1.background = getDrawable(R.drawable.red_rectangle)
            tN2.background = getDrawable(R.drawable.red_rectangle)
            tN3.background = getDrawable(R.drawable.red_rectangle)
            tN4.background = getDrawable(R.drawable.red_rectangle)
            tN5.background = getDrawable(R.drawable.red_rectangle)
            tN6.background = getDrawable(R.drawable.red_rectangle)
            tN1.requestFocus()
            Toast.makeText(this@OTPVerification, "Код неверный", Toast.LENGTH_LONG).show()
        }
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnVerify.setOnClickListener {
                sendRequest()
            }
        }
    }

}