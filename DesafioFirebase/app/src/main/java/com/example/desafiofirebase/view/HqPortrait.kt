package com.example.desafiofirebase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.desafiofirebase.R
import com.example.desafiofirebase.util.Constants.Api.KEY_INTENT_COMIC_IMAGE

class HqPortrait : AppCompatActivity() {

    private val comic: String? by lazy {intent.getStringExtra(KEY_INTENT_COMIC_IMAGE)}
    private val hpBack: ImageButton by lazy {findViewById(R.id.hp_back)}
    private val hpImage: ImageView by lazy {findViewById(R.id.hp_image)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hq_portrait)

        initComponents()
        setupObservables()
    }

    private fun setupObservables() {
        hpBack.setOnClickListener{finish()}
        hpImage.setOnClickListener {finish()}
    }

    private fun initComponents() {
        comic?.let {Glide.with(this).load(it).into(hpImage)}
    }
}