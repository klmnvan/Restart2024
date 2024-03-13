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
    //RED - все тесты не работают (результат Failed)

    /** Тестирование правильного извлечения элемента из очереди */
    @Test
    fun exstarct_isCorrect() {
        val queue = WorkForQueue()
        assertEquals(queue.exstractElement(), OnBoardModel(1,"",""))
    }

    /** Тестирование правильного изменения размера очереди */
    @Test
    fun sizeQueue_isCorrect() {
        val queue = WorkForQueue()
        queue.exstractElement()
        assertEquals(queue.getSizeQueue(), 3)
    }

    /** Тестирование правильной установки надписей на кнопки, в случае, когда очередь не пуста */
    @Test
    fun btnTittle_isCorrect_queueIsNotEmpty() {
        val queue = WorkForQueue()
        queue.exstractElement()
        assertEquals("Правильная надпись на кнопке", queue.getTittleBtn())
    }

    /** Тестирование правильной установки надписи на кнопку, в случае, когда очередь пуста */
    @Test
    fun btnTittle_isCorrect_queueIsEmpty() {
        val queue = WorkForQueue()
        queue.exstractElement()
        queue.exstractElement()
        queue.exstractElement()
        assertEquals("Правильная надпись на кнопке", queue.getTittleBtn())
    }

    /** Тестирование правильного перехода на другую активность или его отсутствие, при
     * нажатии на кнопку, когда очередь не пуста. Не учитывается кнопка Skip */
    @Test
    fun transitionActivity_isCorrect_queueIsNotEmpty() {
        val queue = WorkForQueue()
        queue.exstractElement()
        assertEquals("Переход отсутсвует", queue.pressBtn())
    }

    /** Тестирование правильного перехода на другую активность или его отсутствие, при
     * нажатии на кнопку, когда очередь пуста */
    @Test
    fun transitionActivity_isCorrect_queueIsEmpty() {
        val queue = WorkForQueue()
        queue.exstractElement()
        assertEquals("Имя активности, на которую будет совершён переход", queue.pressBtn())
    }

}