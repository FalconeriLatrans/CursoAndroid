package com.example.desafioandroid.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid.R
import com.example.desafioandroid.view.adapter.MainAdapter
import com.example.desafioandroid.viewModel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private val recyclerView: RecyclerView by lazy {findViewById(R.id.Main_Container)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = HomeViewModel()
        viewModel.getRestaurants()

        setupObservables()

    }
    private fun setupObservables() {
        viewModel.restaurantsLiveData.observe(this, {
            it?.let { restaurants ->
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = MainAdapter(restaurants) { position ->
                        val intent = Intent(this@MainActivity, RestaurantDetailsActivity::class.java)
                        intent.putExtra(KEY_INTENT_RESTAURANT, restaurants[position])
                        startActivity(intent)
                    }
                }
            }
        })
    }
    companion object {
        const val KEY_INTENT_RESTAURANT = "restaurant"
    }
}