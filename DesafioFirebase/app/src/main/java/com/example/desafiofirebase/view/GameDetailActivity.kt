package com.example.desafiofirebase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.desafiofirebase.R
import com.example.desafiofirebase.util.Constants.Intent.KEY_INTENT_GAME
import com.example.desafiofirebase.util.Game

class GameDetailActivity : AppCompatActivity() {

    private val game: Game? by lazy {intent.getParcelableExtra(KEY_INTENT_GAME)}
    private val backbutton: ImageView by lazy {findViewById(R.id.gameDetail_backbutton)}
    private val backgroundImage: ImageView by lazy {findViewById(R.id.gameDetail_background)}
    private val editbutton: FrameLayout by lazy {findViewById(R.id.gameDetail_editButton)}
    private val title: TextView by lazy {findViewById(R.id.gameDetail_title)}
    private val title2: TextView by lazy {findViewById(R.id.gameDetail_title2)}
    private val release: TextView by lazy {findViewById(R.id.gameDetail_release)}
    private val description: TextView by lazy {findViewById(R.id.gameDetail_description)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)

        initComponents()
        setupObservables()

    }

    private fun setupObservables() {
        editbutton.setOnClickListener{
            val intent = Intent(this@GameDetailActivity, GameEditActivity::class.java)
            intent.putExtra(KEY_INTENT_GAME, game)
            startActivity(intent)
            finish()
        }
        backbutton.setOnClickListener{finish()}
    }

    private fun initComponents() {
        game?.let {
            Glide.with(this).load(it.thumbnail).into(backgroundImage)
            title.text = it.title
            title2.text = it.title
            release.text = it.release
            description.text = it.description
        }
    }
}