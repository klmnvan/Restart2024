package com.example.restartchempionat2024.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityOnBoard1Binding
import com.example.restartchempionat2024.models.OnBoardModel
import com.example.restartchempionat2024.models.OnBoardModel1
import com.example.restartchempionat2024.objects.PrefManager
import java.util.LinkedList
import java.util.Queue

class OnBoard1 : AppCompatActivity(), AnimationListener {
    private lateinit var binding: ActivityOnBoard1Binding
    private var queue: Queue<OnBoardModel1> = LinkedList()
    lateinit var animIn: Animation
    lateinit var animOut: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoard1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        createQueue()
        enterQueue(queue.poll())
        pressButton()
        animIn = AnimationUtils.loadAnimation(this@OnBoard1, R.anim.alpha_in)
        animOut = AnimationUtils.loadAnimation(this@OnBoard1, R.anim.alpha_out)
        animIn.setAnimationListener(this)
    }


    private fun pressButton(){
        with(binding){
            btnNext.setOnClickListener {
                with(binding){
                    qImage.startAnimation(animIn)
                    qTittle.startAnimation(animIn)
                    qDescription.startAnimation(animIn)
                }
            }
            btnSkip.setOnClickListener {
                queue.clear()
            }
            btnSignUp.setOnClickListener {
                queue.clear()
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun enterQueue(el: OnBoardModel1){
        with(binding){
            if(queue.size == 0) {
                LLHints.visibility = View.VISIBLE
                btnSignUp.visibility = View.VISIBLE
                btnSkip.visibility = View.GONE
                btnNext.visibility = View.GONE
            }
            qImage.background = getDrawable(el.image)
            qTittle.text = el.tittle
            qDescription.text = el.description
            initPoint(el.points)
        }
    }

    fun getStrModel(model: OnBoardModel1): String{
        return "${model.image};${model.tittle};${model.description};${model.points}"
    }

    private fun initPoint(count: Int){
        with(binding){
            when(count){
                1 -> {
                    p1.background = getDrawable(R.drawable.oval_blue)
                    p2.background = getDrawable(R.drawable.oval_blue_corners)
                    p3.background = getDrawable(R.drawable.oval_blue_corners)
                }
                2 -> {
                    p1.background = getDrawable(R.drawable.oval_blue_corners)
                    p2.background = getDrawable(R.drawable.oval_blue)
                    p3.background = getDrawable(R.drawable.oval_blue_corners)
                }
                3 -> {
                    p1.background = getDrawable(R.drawable.oval_blue_corners)
                    p2.background = getDrawable(R.drawable.oval_blue_corners)
                    p3.background = getDrawable(R.drawable.oval_blue)
                }
            }
        }
    }

    private fun createQueue(){
        queue = LinkedList(
            listOf(
                OnBoardModel1(R.drawable.image_onboard1, "Quick Delivery At Your Doorstep", "Enjoy quick pick-up and delivery to your destination", 1),
                OnBoardModel1(R.drawable.image_onboard2, "Flexible Payment", "Different modes of payment either before and after delivery without stress", 2),
                OnBoardModel1(R.drawable.image_onboard3, "Real-time Tracking", "Track your packages/items from the comfort of your home till final destination", 3)
            )
        )
    }

    override fun onAnimationStart(p0: Animation?) {
    }

    override fun onAnimationEnd(p0: Animation?) {
        with(binding){
            enterQueue(queue.poll())
            qImage.startAnimation(animOut)
            qTittle.startAnimation(animOut)
            qDescription.startAnimation(animOut)
        }
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }
}