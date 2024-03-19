package com.example.restartchempionat2024.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.objects.PrefManager
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme
import java.util.concurrent.TimeUnit

@SuppressLint("CustomSplashScreen")
class SplashScreen : ActivityCustomTheme() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    TimeUnit.SECONDS.sleep(3)
                    if (PrefManager.indAct == 0){
                        startActivity(Intent(this@SplashScreen, OnBoard1::class.java))
                        finish()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()

        /*val thread1 = object : Thread(){
            override fun run() {
                try {
                    TimeUnit.SECONDS.sleep(3)

                } catch (e: Exception) {

                }
            }
        }
        thread1.start()*/
    }
}