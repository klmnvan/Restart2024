package com.example.restartchempionat2024.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.databinding.ActivityOnBoardBinding
import com.example.restartchempionat2024.models.OnBoardModel
import com.example.restartchempionat2024.objects.PrefManager
import java.util.LinkedList
import java.util.Queue

/** Класс активности OnBoard, содержит очередь данных на экране, которые будут извлекаться,
 * когда пользователь будет нажимать на кнопку */
class OnBoard : AppCompatActivity(), AnimationListener {
    private lateinit var binding: ActivityOnBoardBinding
    private var queue: Queue<OnBoardModel> = LinkedList()
    private lateinit var inAnimation: Animation
    private lateinit var outAnimation: Animation
    private var currentImageId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /* проверяем, была ли создана очередь (по дефолту queueIsCreate - false) */
        if (PrefManager.queueIsCreate) {
            restoreQueue()
        } else {
            createQueue()
        }
        /* сразу извлекаем из очереди объект и отрисовываем его */
        enterOnBoard(queue.poll()!!)
        pressingButton()
        initAnimation()
    }

    /** Функция, где инициализируется анимация */
    private fun initAnimation() {
        inAnimation = AnimationUtils.loadAnimation(this@OnBoard, R.anim.alpha_in)
        outAnimation = AnimationUtils.loadAnimation(this@OnBoard, R.anim.alpha_out)
        inAnimation.setAnimationListener(this@OnBoard)
    }

    /** Функция, где обрабатываются нажатия на кнопки */
    private fun pressingButton() {
        with(binding) {
            btnNext.setOnClickListener {
                img.startAnimation(inAnimation)
                tTittle.startAnimation(inAnimation)
                tDescription.startAnimation(inAnimation)
            }
            btnSkip.setOnClickListener {
                PrefManager.indAct = 1
                queue.clear()
                startActivity(Intent(this@OnBoard, Holder::class.java))
                finish()
            }
            btnSignIn.setOnClickListener {
                PrefManager.indAct = 1
                queue.clear()
                startActivity(Intent(this@OnBoard, Holder::class.java))
                finish()
            }
            btnSignUp.setOnClickListener {
                PrefManager.indAct = 1
                queue.clear()
                startActivity(Intent(this@OnBoard, Holder::class.java))
                finish()
            }
        }
    }

    /** Функция для отрисовки очереди */
    fun enterOnBoard(onBoardModel: OnBoardModel) {
        with(binding) {
            img.setImageResource(onBoardModel.image)
            currentImageId = onBoardModel.image
            tTittle.text = onBoardModel.tittle
            tDescription.text = onBoardModel.description
            if (queue.size == 0) {
                showHints()
            }
            saveQueue()
        }
    }

    /** Функция для создания и заполнения очереди при первом запуске */
    private fun createQueue() {
        queue = LinkedList(
            listOf(
                OnBoardModel(
                    R.drawable.image_onboard1,
                    "Quick Delivery At Your Doorstep",
                    "Enjoy quick pick-up and delivery to your destination"
                ),
                OnBoardModel(
                    R.drawable.image_onboard2,
                    "Flexible Payment",
                    "Different modes of payment either before and after delivery without stress"
                ),
                OnBoardModel(
                    R.drawable.image_onboard3,
                    "Real-time Tracking",
                    "Track your packages/items from the comfort of your home till final destination"
                )
            )
        )
        PrefManager.queueIsCreate = true
    }

    /** Функция для восстановления очереди из сохраненных данных */
    private fun restoreQueue() {
        val strQueue: String = PrefManager.queueString
        strQueue.split('|').forEach {
            queue.offer(getQueueFromStr(it))
        }
    }

    /** Функция, которая сохраняет содержимое очереди   */
    private fun saveQueue() {
        with(binding) {
            val img = currentImageId
            val str1 = tTittle.text.toString()
            val str2 = tDescription.text.toString()
            //Не только то, что в очереди находится, а ещё и то, что на экране,
            //потому что из очереди извлекается и удаляется то, что на экране
            if(queue.size != 0) {
                PrefManager.queueString =
                    "${img};${str1};${str2}|" + queue.joinToString("|") { getStrModel(it) }
            } else { PrefManager.queueString = "${img};${str1};${str2}" }
        }
    }

    /** Функция, которая преобразует модель в строку, где разделитель ';'  */
    private fun getStrModel(model: OnBoardModel): String {
        return "${model.image};${model.tittle};${model.description}"
    }

    /** Функция, которая преобразует строку в модель, где разделитель ';'  */
    private fun getQueueFromStr(str: String): OnBoardModel {
        val aStr = str.split(';')
        return OnBoardModel(aStr[0].toInt(), aStr[1], aStr[2])
    }

    /** Функция, которая отображает текст-подсказку, когда очередь пуста  */
    private fun showHints() {
        with(binding){
            btnNext.visibility = View.GONE
            btnSkip.visibility = View.GONE
            btnSignUp.visibility = View.VISIBLE
            LLHints.visibility = View.VISIBLE
        }
    }

    override fun onAnimationStart(p0: Animation?) {

    }

    /** Функция, которая срабатывает после окнчаня анимации */
    override fun onAnimationEnd(p0: Animation?) {
        with(binding) {
            enterOnBoard(queue.poll()!!)
            img.startAnimation(outAnimation)
            tTittle.startAnimation(outAnimation)
            tDescription.startAnimation(outAnimation)
        }
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }


}