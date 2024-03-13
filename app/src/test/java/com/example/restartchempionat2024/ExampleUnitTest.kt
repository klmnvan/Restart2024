package com.example.restartchempionat2024

import com.example.restartchempionat2024.logicClass.WorkForQueue
import com.example.restartchempionat2024.models.OnBoardModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    //REFACTOR - изменяем 3 теста, что-то улучшаем (можно заранее написать неупрощенно)

    /** Тестирование правильного извлечения элемента из очереди */
    @Test
    fun exstarct_isCorrect() {
        val queue = WorkForQueue()
        val expectedElement = OnBoardModel(
            R.drawable.image_onboard1,
            "Quick Delivery At Your Doorstep",
            "Enjoy quick pick-up and delivery to your destination"
        )
        val actualElement = queue.exstractElement()
        assertEquals(actualElement, expectedElement)
    }

    /** Тестирование правильного изменения размера очереди */
    @Test
    fun sizeQueue_isCorrect() {
        val queue = WorkForQueue()
        queue.exstractElement() //извлекли элемент из очереди, размер которой изначально равен 3
        val actualSize = queue.getSizeQueue() //получили размер очереди после извлечения
        val correctSize = 2
        assertEquals(correctSize, actualSize)
    }

    /** Тестирование правильной установки надписей на кнопки, в случае, когда очередь не пуста */
    @Test
    fun btnTittle_isCorrect_queueIsNotEmpty() {
        val queue = WorkForQueue()
        queue.exstractElement()
        val actualTittleBtn = queue.getTittleBtn()
        val correctTittleBtn = "Next"
        assertEquals(correctTittleBtn, actualTittleBtn)
    }

    /** Тестирование правильной установки надписи на кнопку, в случае, когда очередь пуста */
    @Test
    fun btnTittle_isCorrect_queueIsEmpty() {
        val queue = WorkForQueue()
        queue.clearQueue()
        val actualTittleBtn = queue.getTittleBtn()
        val correctTittleBtn = "Sign Up"
        assertEquals(correctTittleBtn, actualTittleBtn)
    }

    /** Тестирование правильного перехода на другую активность или его отсутствие, при
     * нажатии на кнопку, когда очередь не пуста. Не учитывается кнопка Skip */
    @Test
    fun transitionActivity_isCorrect_queueIsNotEmpty() {
        val queue = WorkForQueue()
        queue.exstractElement()
        val actualTransition = queue.pressBtn()
        val correctTransition = null
        assertEquals(correctTransition, actualTransition)
    }

    /** Тестирование правильного перехода на другую активность или его отсутствие, при
     * нажатии на кнопку, когда очередь пуста */
    @Test
    fun transitionActivity_isCorrect_queueIsEmpty() {
        val queue = WorkForQueue()
        queue.clearQueue()
        val actualTransition = queue.pressBtn()
        val correctTransition = "Holder"
        assertEquals(actualTransition, correctTransition)
    }

}