package com.example.exercicioaula27

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MessageScreen : AppCompatActivity() {
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_screen)

        user = intent

    }

}