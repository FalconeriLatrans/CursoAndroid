package com.example.desafiofirebase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiofirebase.R
import com.example.desafiofirebase.viewModel.HomeViewModel
import com.example.desafiofirebase.util.Constants.Api.KEY_INTENT_COMIC
import com.example.desafiofirebase.view.adapter.HomeAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private val container: RecyclerView by lazy {findViewById(R.id.home_Container)}
    private val homeViewModel: HomeViewModel by lazy { HomeViewModel() }
    private val auth by lazy {Firebase.auth}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                }
            }

        }

    override fun onResume() {
        super.onResume()
        auth.currentUser?.let {
            initComponents()
            setupObservables()
        }?: run {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

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