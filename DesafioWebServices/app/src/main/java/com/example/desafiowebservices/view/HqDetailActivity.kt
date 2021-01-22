package com.example.desafiowebservices.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.desafiowebservices.R
import com.example.desafiowebservices.model.Comics.Result
import com.example.desafiowebservices.util.Constants.Api.KEY_INTENT_COMIC
import com.example.desafiowebservices.util.Constants.Api.KEY_INTENT_COMIC_IMAGE

class HqDetailActivity : AppCompatActivity() {

    private val comic: Result? by lazy {intent.getParcelableExtra(KEY_INTENT_COMIC)}
    private val backgroundImage: ImageView by lazy {findViewById(R.id.hqDetail_background)}
    private val backbutton: ImageView by lazy {findViewById(R.id.hqDetail_backbutton)}
    private val hqImage: ImageView by lazy {findViewById(R.id.hqDetail_image)}
    private val hqDescription: TextView by lazy {findViewById(R.id.hqDetail_description)}
    private val hqPages: TextView by lazy {findViewById(R.id.hqDetail_pages)}
    private val hqPrice: TextView by lazy {findViewById(R.id.hqDetail_price)}
    private val hqDate: TextView by lazy {findViewById(R.id.hqDetail_published)}
    private val hqTitle: TextView by lazy {findViewById(R.id.hqDetail_title)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hq_detail)

        initComponents()
        setupObservables()

    }

    private fun setupObservables() {
        hqImage.setOnClickListener{
            val intent = Intent(this@HqDetailActivity, HqPortrait::class.java)
            intent.putExtra(KEY_INTENT_COMIC_IMAGE, comic?.thumbnail?.getThumb())
            startActivity(intent)
        }
        backbutton.setOnClickListener{finish()}
    }

    private fun initComponents() {
        comic?.let {
            val funciona = it.thumbnail.getThumb()
            Glide.with(this).load(funciona).into(hqImage)
            hqDescription.text = it.description
            hqPages.text = it.pageCount.toString()
            hqPrice.text = it.prices[0].getPrice()
            hqDate.text = it.dates[0].getDateAsString()
            hqTitle.text = it.title
        }
    }
}