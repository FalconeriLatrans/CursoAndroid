package com.example.desafioandroid.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.desafioandroid.R
import com.example.desafioandroid.viewModel.SplashViewModel
import java.util.Timer
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    private val imageView: ImageView by lazy {findViewById(R.id.sp_image)}
    private lateinit var handler: Handler
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = SplashViewModel()

        setupSplash()
    }

    override fun onResume() {
        super.onResume()
        delayAndGo(1000L)
    }

    private fun delayAndGo(delay: Long) {
        val intent = Intent(this, LoginActivity::class.java)

        Timer("SettingUp", false).schedule(delay) {
            startActivity(intent)
            finish();
        }
    }

    private fun setupSplash(){
        Glide.with(this).load(viewModel.imageSrc).into(imageView)
    }
}