package com.example.desafiofirebase.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiofirebase.util.Constants.Database.DATABASE_NAME
import com.example.desafiofirebase.util.Constants.Database.DB_CREATED_BY
import com.example.desafiofirebase.util.Constants.Database.DB_DESCRIPTION
import com.example.desafiofirebase.util.Constants.Database.DB_ID
import com.example.desafiofirebase.util.Constants.Database.DB_RELEASE
import com.example.desafiofirebase.util.Constants.Database.DB_THUMBNAIL
import com.example.desafiofirebase.util.Constants.Database.DB_TITLE
import com.example.desafiofirebase.util.Game
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var homeGameList: MutableLiveData<List<Game>> = MutableLiveData()
    private var fullGameList: MutableList<Game> = mutableListOf()

    private val firebaseDatabase by lazy { Firebase.firestore }

    fun getGames() {
        Log.i("HomeViewModel", "Chamando lista de games")
        viewModelScope.launch {
            fullGameList = mutableListOf()
            firebaseDatabase.collection(DATABASE_NAME)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val tempGame = Game(document[DB_ID].toString(), document[DB_THUMBNAIL].toString(),document[DB_TITLE].toString(),document[DB_RELEASE].toString(),document[DB_DESCRIPTION].toString(),document[DB_CREATED_BY].toString())
                        fullGameList.add(tempGame)
                    }
                    homeGameList.postValue(fullGameList)
                }
                .addOnFailureListener { exception ->
                    Log.i("HomeViewModel", "Erro ao receber dados do Firebase. ",exception)
                }
        }
    }
    fun filter(argument: String){
        Log.i("HomeViewModel", "Filtrando a lista com o argumento: '${argument}'")
        val tempGameList: List<Game>?
        tempGameList = fullGameList.filter {it.title.contains(argument,true)}
        homeGameList.postValue(tempGameList)
    }
}