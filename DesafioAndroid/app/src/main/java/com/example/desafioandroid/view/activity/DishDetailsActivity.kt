package com.example.desafioandroid.view.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.desafioandroid.R
import com.example.desafioandroid.model.Dish
import com.example.desafioandroid.view.activity.RestaurantDetailsActivity.Companion.KEY_INTENT_DISH

class DishDetailsActivity : AppCompatActivity() {

    private val dish: Dish? by lazy {intent.getParcelableExtra(KEY_INTENT_DISH)}
    private val backButton: ImageView by lazy {findViewById(R.id.dd_backbutton)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_details)

        setupDish()
        backButton.setOnClickListener {finish()}
    }
    private fun setupDish(){
        Glide.with(this).load(dish?.imagesrc).into(findViewById<ImageView>(R.id.dd_image))
        findViewById<TextView>(R.id.dd_name).text = dish?.name
//TODO        findViewById<TextView>(R.id.dd_description).text = dish?.description
    }
}