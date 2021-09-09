package com.example.service

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.*

class ServiceRandomNumbers : Service() {

    // Handler - очередь сообщений
    // Возможности: 1.реализ. отложенное по времени вып-е кода 2. вып-е кода не в своем потоке

    // Для того чтобы обнулить в методе Destroy, необходимо сделать null-able
    private var handler: Handler? = null

    // Runnable - интерфейс для реализации потока, при помощи абстр.метода run()
    private var runnable:Runnable? = null

    // Музыка в фоне
    private var mediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer?.isLooping
        mediaPlayer?.start()
    }

    // Запуск при нажатии на кнопку "старт"
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler = Handler()

        val showInfo: Runnable = object : Runnable {
            override fun run() {
                val rand = Random()
                val number: Int = rand.nextInt(1000)
                // Вывод в консоль
                Log.e(TAG, "Число:  $number")
                handler!!.postDelayed(this, 5000)
            }
        }

        handler!!.postDelayed(showInfo, 5000)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Удаляем Runnable-объект для прекращения задачи
        // аргумент it является не nullable-версией
        runnable?.let { handler?.removeCallbacks(it) }
        // остановка музыки
        mediaPlayer?.stop()
    }
}

/*
  val showInfo: Runnable = object : Runnable {
            override fun run() {
                val rand = Random()
                val number: Int = rand.nextInt(1000)
                // Вывод в консоль
                Log.e(TAG, "Число:  $number")
                handler.postDelayed(this, 1000)
            }
        }
* */