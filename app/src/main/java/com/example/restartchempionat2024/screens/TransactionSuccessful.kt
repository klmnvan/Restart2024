package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityPackageInfoBinding
import com.example.restartchempionat2024.databinding.ActivityTtransactionSuccessfulBinding
import com.example.restartchempionat2024.objects.UserData
import com.example.restartchempionat2024.theme.ActivityCustomTheme

class TransactionSuccessful : ActivityCustomTheme(), AnimationListener {
    lateinit var binding: ActivityTtransactionSuccessfulBinding
    private lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTtransactionSuccessfulBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAnimation()
        binding.tTrackNum.text = UserData.lastOrder.trackingNumber
    }

    /** Функция, где инициализируется анимация */
    private fun initAnimation() {
        animation = AnimationUtils.loadAnimation(this@TransactionSuccessful, R.anim.rotate_2_full)
        animation.setAnimationListener(this)
        binding.progress.startAnimation(animation)
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnTrack.setOnClickListener {
                UserData.nulableLastOrder()
                startActivity(Intent(this@TransactionSuccessful, Track::class.java))
                finish()
            }
            btnGoBack.setOnClickListener {
                UserData.nulableLastOrder()
                startActivity(Intent(this@TransactionSuccessful, Home::class.java))
                finish()
            }
        }
    }

    override fun onAnimationStart(p0: Animation?) {

    }

    override fun onAnimationEnd(p0: Animation?) {
        with(binding){
            pressingButton()
            progress.visibility = View.INVISIBLE
            progress2.visibility = View.VISIBLE
        }
    }

    override fun onAnimationRepeat(p0: Animation?) {

    }
}