package com.example.desafioandroid.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafioandroid.R
import com.example.desafioandroid.model.Restaurant
import com.example.desafioandroid.view.activity.MainActivity.Companion.KEY_INTENT_RESTAURANT
import com.example.desafioandroid.view.adapter.RdAdapter
import com.example.desafioandroid.viewModel.RestaurantViewModel

class RestaurantDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: RestaurantViewModel
    private val restaurant: Restaurant? by lazy {intent.getParcelableExtra(KEY_INTENT_RESTAURANT)}
    private val recyclerView: RecyclerView by lazy {findViewById(R.id.rd_Container)}
    private val backbutton: ImageView by lazy {findViewById(R.id.rd_backbutton)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)

        setupRestaurant()

        viewModel = RestaurantViewModel()
        viewModel.getDishes(restaurant?.id)

        setupObservables()

        backbutton.setOnClickListener {finish()}
    }
    private fun setupRestaurant() {
        Glide.with(this).load(restaurant?.imagesrc).into(findViewById<ImageView>(R.id.rd_image))
        findViewById<TextView>(R.id.rd_name).text = restaurant?.name
    }
    private fun setupObservables() {
        viewModel.dishesLiveData.observe(this, {
            it?.let { dishes ->
                recyclerView.apply {
                    layoutManager = GridLayoutManager(this@RestaurantDetailsActivity,2)
                    adapter = RdAdapter(dishes) { position ->
                        val intent = Intent(this@RestaurantDetailsActivity, DishDetailsActivity::class.java)
                        intent.putExtra(KEY_INTENT_DISH, dishes[position])
                        startActivity(intent)
                    }
                }
            }
        })
    }
    companion object {
        const val KEY_INTENT_DISH = "dish"
    }
}