package com.example.desafiowebservices.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservices.R
import com.example.desafiowebservices.model.HomeViewModel
import com.example.desafiowebservices.util.Constants.Api.KEY_INTENT_COMIC
import com.example.desafiowebservices.view.adapter.HomeAdapter

class HomeActivity : AppCompatActivity() {

    private val container: RecyclerView by lazy {findViewById(R.id.home_Container)}
    private val homeViewModel: HomeViewModel by lazy {HomeViewModel()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupObservables()
        initComponents()

    }

    private fun initComponents() {
        homeViewModel.getHomeComics()

    }

    private fun setupObservables() {
        homeViewModel.homeComicsList.observe(this, {
            it?.let { comics ->
                container.apply {
                    layoutManager = GridLayoutManager(this@HomeActivity,3)
                    adapter = HomeAdapter(comics) { position ->
                        val intent = Intent(this@HomeActivity, HqDetailActivity::class.java)
                        intent.putExtra(KEY_INTENT_COMIC, comics[position])
                        startActivity(intent)
                    }
                }
            }
        })
    }
}