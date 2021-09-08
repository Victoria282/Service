package com.example.service

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.*

class ServiceRandomNumbers : Service() {

    // Handler - очередь сообщений
    // Возможности: 1.реализ. отложенное по времени вып-е кода 2. вып-е кода не в своем потоке
    private lateinit var handler: Handler

    // Генерация ранд.чисел
    private lateinit var rand: Random

    // Runnable - интерфейс для реализации потока, при помощи абстр.метода run()
    private lateinit var runnable:Runnable

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    // Запуск при нажатии на кнопку "старт"
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler = Handler()

        runnable = Runnable {
            kotlin.run {
                val rand = Random()
                val number: Int = rand.nextInt(1000)
                // Вывод в консоль
                Log.e(TAG, "Число:  $number")
                handler.postDelayed(runnable, 10000)
            }
        }
        // повторяем действие через 1000 миллисекунд
        handler.postDelayed(runnable, 10000)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Удаляем Runnable-объект для прекращения задачи
        handler.removeCallbacks(runnable)
    }
}