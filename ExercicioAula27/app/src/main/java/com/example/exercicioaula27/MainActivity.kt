package com.example.exercicioaula27

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val users = mutableListOf<User>()

        users.add(User("Alberto","Lembra de mim?", "SEMANA PASSADA",12))
        users.add(User("Bernardo","Tchau, boa noite", "ONTEM",0))




    }
}