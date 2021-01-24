package com.example.desafiofirebase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiofirebase.R
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onResume() {
        super.onResume()
        delayAndGo(1000L)
    }

    private fun delayAndGo(delay: Long) {
        val intent = Intent(this, HomeActivity::class.java)

        Timer("SettingUp", false).schedule(delay) {
            startActivity(intent)
            finish();
        }
    }

}