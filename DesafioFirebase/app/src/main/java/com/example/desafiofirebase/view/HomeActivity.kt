package com.example.desafiofirebase.view

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiofirebase.R
import com.example.desafiofirebase.util.Constants.Codes.SPEECH_REQUEST_CODE
import com.example.desafiofirebase.viewModel.HomeViewModel
import com.example.desafiofirebase.util.Constants.Intent.KEY_INTENT_GAME
import com.example.desafiofirebase.util.Constants.SharedPreferences.KEY_SP_DBNAME
import com.example.desafiofirebase.util.Constants.SharedPreferences.KEY_SP_LOGIN_REMEMBER
import com.example.desafiofirebase.view.adapter.HomeAdapter
import com.google.android.material.textfield.TextInputLayout

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private val container: RecyclerView by lazy { findViewById(R.id.home_Container) }
    private val addButton: Button by lazy { findViewById(R.id.home_addButton) }
    private val searchField: TextInputLayout by lazy { findViewById(R.id.hm_search_field) }
    private val homeViewModel: HomeViewModel by lazy { HomeViewModel() }
    private val firebaseAuth by lazy { Firebase.auth }
    private val sharedPreferences: SharedPreferences by lazy {getSharedPreferences(KEY_SP_DBNAME,MODE_PRIVATE)}
    private var comingSearch: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (!sharedPreferences.getBoolean(KEY_SP_LOGIN_REMEMBER, false)) {
            firebaseAuth.signOut()
        }

        searchField.editText?.setText("Search Games")
        setupObservables()

    }

    override fun onStart() {
        super.onStart()
        initComponents()
    }

    override fun onResume() {
        super.onResume()
        firebaseAuth.currentUser?.let {
            if (comingSearch != "") {
                homeViewModel.filter(comingSearch)
                searchField.editText?.setText(comingSearch)
                comingSearch = ""
            } else if (searchField.editText?.text.toString() == "Search Games"){
                homeViewModel.filter("")
            } else {
                homeViewModel.filter(searchField.editText?.text.toString())
            }
        } ?: run {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!sharedPreferences.getBoolean(KEY_SP_LOGIN_REMEMBER, false)) {
            firebaseAuth.signOut()
        }
    }

    private fun  initComponents() {
        Log.i("HomeActivity", "Chamando lista de games")
        homeViewModel.getGames()
    }

    private fun setupObservables() {
        Log.i("HomeActivity", "Configurando recycler view")
        homeViewModel.homeGameList.observe(this, {
            it?.let { gameList ->
                container.apply {
                    layoutManager = GridLayoutManager(this@HomeActivity, 3)
                    adapter = HomeAdapter(gameList) { position ->
                        val intent = Intent(this@HomeActivity, GameDetailActivity::class.java)
                        intent.putExtra(KEY_INTENT_GAME, gameList[position])
                        startActivity(intent)
                    }
                }
            }
        })
        addButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, GameEditActivity::class.java)
            startActivity(intent)
        }
        searchField.editText?.setOnClickListener {
            if (searchField.editText?.text.toString() == "Search Games"){
                searchField.editText?.setText("")
            }
        }
        searchField.editText?.doOnTextChanged { text, _, _, _ ->
            Log.i("HomeActivity", "Texto modificado")
            homeViewModel.filter(text.toString())
        }
        searchField.setEndIconOnClickListener {
            val listen = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            listen.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            startActivityForResult(listen, SPEECH_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == SPEECH_REQUEST_CODE) {
            val spokenText: String = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0) ?: ""
            Log.i("HomeActivity", "Busca por voz: $spokenText")
            comingSearch = spokenText
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}