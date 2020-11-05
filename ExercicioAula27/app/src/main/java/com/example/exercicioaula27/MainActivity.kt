package com.example.exercicioaula27

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_list_item.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val users = mutableListOf<User>()

        users.add(User("Alberto","Lembra de mim?", "SEMANA PASSADA",12))
        users.add(User("Bernardo","Tchau, boa noite", "ONTEM",0))

        findViewById<RecyclerView>(R.id.Main_Container).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(users){position ->
                val intent = Intent(this@MainActivity, R.layout.activity_message_screen::class.java)
                intent.putExtra(KEY_INTENT_USER, users[position])
                startActivity(intent)
            }
        }
    }
    companion object {
        const val KEY_INTENT_USER = "user"
    }
}