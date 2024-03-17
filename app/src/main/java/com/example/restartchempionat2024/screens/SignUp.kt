package com.example.restartchempionat2024.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.widget.addTextChangedListener
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivitySignUpBinding
import com.example.restartchempionat2024.objects.General.isEmailValid
import com.example.restartchempionat2024.objects.Requests
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class SignUp : ActivityCustomTheme() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressingButton()
        checkInputFields()
    }

    /** Функция, где отслеживаются изменения полей ввода */
    private fun checkInputFields() {
        with(binding) {
            inptName.addTextChangedListener {
                setStyles()
            }
            inptPhone.addTextChangedListener {
                setStyles()
            }
            inptEmail.addTextChangedListener {
                setStyles()
            }
            inptPassword.addTextChangedListener {
                setStyles()
            }
            inptPassword2.addTextChangedListener {
                setStyles()
            }
            checkbox.setOnCheckedChangeListener { compoundButton, b -> setStyles() }
        }
    }

    /** Функция, где в зависимости от заполнения полей красится кнопка в соответствующий цвет */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setStyles() {
        with(binding) {
            if (inptEmail.text.isNotEmpty() && inptPassword.text!!.isNotEmpty() &&
                inptName.text.isNotEmpty() && inptPhone.text.isNotEmpty() &&
                inptPassword2.text!!.isNotEmpty() && checkbox.isChecked) {
                btnSignUp.background = getDrawable(R.drawable.blue_5r)
            } else {
                btnSignUp.background = getDrawable(R.drawable.gray_5r)
            }
        }

    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnSignIn.setOnClickListener {
                startActivity(Intent(this@SignUp, LogIn::class.java))
                finish()
            }
            btnSignUp.setOnClickListener {
                sendRequest()
            }
            bOpenPdf.setOnClickListener {
                verifyPremissions()
            }
        }
    }

    /** Функция, где проверяется наличие разрешений для открытия pdf файла */
    private fun verifyPremissions() {
        val p = ActivityCompat.checkSelfPermission(this@SignUp, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val p1 = ActivityCompat.checkSelfPermission(this@SignUp, Manifest.permission.READ_EXTERNAL_STORAGE)
        if(p == PackageManager.PERMISSION_GRANTED && p1 == PackageManager.PERMISSION_GRANTED
            && Environment.isExternalStorageManager()) {
            openPdf()
        } else {
            val ps = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this@SignUp, ps, 1)
            val intent = Intent()
            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            val uri = Uri.fromParts("package", packageName, null)
            intent.setData(uri)
            startActivity(intent)
        }
    }

    /** Функция, где обрабатываются открытие pdf файла */
    private fun openPdf() {
        val inputStream = resources.openRawResource(R.raw.my_file)
        inputStream.use {
            val file = File(cacheDir, "my_file.pdf")
            FileOutputStream(file).use { o ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (inputStream.read(buffer).also { read = it } != -1){
                    o.write(buffer, 0, read)
                }
                o.flush()
            }
        }
        val cacheFile = File(cacheDir, "my_file.pdf")
        val uri = FileProvider.getUriForFile(this@SignUp, "$packageName.provider", cacheFile)
        if(uri != null){
            val intentPdf = Intent(Intent.ACTION_VIEW)
            intentPdf.data = uri
            intentPdf.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(intentPdf, "pdf"))
        }
    }

    /** Функция, где отправляется и обрабатывает запрос на сервер */
    private fun sendRequest() {
        with(binding){
            val email = inptEmail.text.toString()
            val pass = inptPassword.text.toString()
            //Пока не понятно, куда девать name и phone
            val name = inptName.text.toString()
            val phone = inptPhone.text.toString()
            if(email.isEmailValid()){
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        Toast.makeText(this@SignUp, "Идёт регистрация...", Toast.LENGTH_LONG).show()
                        Requests.signUp(email, pass)
                        //Получить профиль пользователя, чтобы узнать uuid
                        UserData.profile = Requests.getProfile(email)
                        //Вставить данные в таблицу с профилями (или что-то другое)
                        Requests.updateProfile(name, phone, UserData.profile.id!!)
                        runOnUiThread {
                            startActivity(Intent(this@SignUp, LogIn::class.java))
                            finish()
                        }

                    } catch (e: Exception) {
                        Toast.makeText(this@SignUp, e.message.toString(), Toast.LENGTH_LONG).show()
                        Log.d("sendRequest + $this", e.message.toString())
                    }
                }
            } else {
                Toast.makeText(this@SignUp, "email не валиден", Toast.LENGTH_LONG).show()
            }

        }

    }
}