package com.example.restartchempionat2024.logicClass

import com.example.restartchempionat2024.R
import com.example.restartchempionat2024.models.OnBoardModel
import java.util.LinkedList
import java.util.Queue

class WorkForQueue {

    private var queue: Queue<OnBoardModel> = LinkedList(
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

    /** Извлекается 1 элемент из очереди */
    fun exstractElement(): OnBoardModel? {
        return queue.poll()
    }

    /** Возвращает размер очереди */
    fun getSizeQueue(): Int {
        return queue.size
    }

    /** Возвращает заголовки(ок) кнопок(и), которые(ый) доступны(ен) в зависимости от количества элементов в очереди */
    fun getTittleBtn(): String {
        return if(queue.size != 0){
            "Next and Skip"
        } else {
            "Sign Up"
        }
    }

    /** Возвращает название активности, на которую будет совершен переход при нажатии на кнопку */
    /** Вернёт null, если перехода не будет */
    fun pressBtn(): String? {
        return if (queue.size > 0){
            "Holder"
        } else {
            null
        }
    }

    /** Очищает очередь */
    fun clearQueue() {
        queue.clear()
    }




}