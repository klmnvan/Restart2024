package com.example.restartchempionat2024.screens

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityLogInBinding
import com.example.restartchempionat2024.databinding.ActivityOnBoardBinding
import com.example.restartchempionat2024.databinding.ActivityOtpverificationBinding

class OTPVerification : AppCompatActivity() {
    private lateinit var binding: ActivityOtpverificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}