package com.example.service

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: Button = findViewById(R.id.buttonStart)
        val buttonStop: Button = findViewById(R.id.buttonStop)

        buttonStart.setOnClickListener() {
            // Запуск сервиса
            startService(Intent(this, ServiceRandomNumbers::class.java))
            Log.e(TAG, "Нажата кнопка Start!")
        }

        buttonStop.setOnClickListener() {
            // Остановка сервиса
            stopService(Intent(this, ServiceRandomNumbers::class.java))
            Log.e(TAG, "Нажата кнопка Stop!")
        }

    }
}