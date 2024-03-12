package com.example.restartchempionat2024.screens

import android.os.Bundle
import android.os.PersistableBundle
import android.view.animation.Animation
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityOnBoardBinding
import com.example.restartchempionat2024.models.OnBoardModel
import com.example.restartchempionat2024.objects.PrefManager
import java.util.LinkedList
import java.util.Queue

class OnBoard : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardBinding
    private var queue: Queue<OnBoardModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //проверяем, была ли создана очередь (по дефолту queueIsCreate - false)
        if (PrefManager.queueIsCreate) {
            restoreQueue()
        } else {
            createQueue()
        }
        enterOnBoard(queue?.poll()!!)
        pressingButton()
    }

    private fun pressingButton(){
        with(binding) {
            btnNext.setOnClickListener {

            }
            btnSkip.setOnClickListener {

            }
            btnSignIn.setOnClickListener {

            }
            btnSignUp.setOnClickListener {

            }
        }
    }

    /** Функция для отрисовки очереди */
    fun enterOnBoard(onBoardModel: OnBoardModel) {
        with(binding) {
            img.setImageResource(onBoardModel.image)
            tTittle.text = onBoardModel.tittle
            tDescription.text = onBoardModel.description
        }
    }


    /** Функция для создания и заполнения очереди при первом запуске */
    private fun createQueue() {
        queue = LinkedList(
            listOf(
                OnBoardModel(R.drawable.image_onboard1,"Quick Delivery At Your Doorstep", "Enjoy quick pick-up and delivery to your destination"),
                OnBoardModel(R.drawable.image_onboard2, "Flexible Payment", "Different modes of payment either before and after delivery without stress"),
                OnBoardModel(R.drawable.image_onboard3, "Real-time Tracking", "Track your packages/items from the comfort of your home till final destination")
            )
        )
        PrefManager.queueIsCreate = true
    }

    /** Функция для восстановления очереди из сохраненных данных */
    private fun restoreQueue() {
        val strQueue = PrefManager.queueString
        strQueue.split('|').forEach {
            queue?.offer(getQueueFromStr(it))
        }
    }

    /** Функция, которая сохраняет содержимое очереди при уходе приложения в фоновый режим  */
    override fun onPause() {
        super.onPause()
        with(binding) {
            val img = img.id
            val str1 = tTittle.text.toString()
            val str2 = tTittle.text.toString()
            //Не только то, что в очереди находится, а ещё и то, что на экране,
            //потому что из очереди извлекается и удаляется то, что на экране
            PrefManager.queueString =
                "${img};${str1};${str2}|" + queue?.joinToString("|") { getStrModel(it) }
        }
    }

    /** Функция, которая преобразует модель в строку, где разделитель ';'  */
    private fun getStrModel(model: OnBoardModel): String {
        return "${model.image};${model.tittle};${model.description}"
    }

    /** Функция, которая преобразует строку в модель, где разделитель ';'  */
    fun getQueueFromStr(str: String): OnBoardModel {
        val aStr = str.split(';')
        return OnBoardModel(aStr[0].toInt(), aStr[1], aStr[2])
    }


}