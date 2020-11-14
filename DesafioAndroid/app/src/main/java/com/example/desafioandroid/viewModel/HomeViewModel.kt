package com.example.desafioandroid.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioandroid.model.Restaurant
import com.example.desafioandroid.model.RestaurantData

class HomeViewModel : ViewModel(){
    val restaurantsLiveData: MutableLiveData<List<Restaurant>> = MutableLiveData()
    private val homeData = RestaurantData()

    fun getRestaurants() {
        Log.i("Tela 1", "Solicitando lista de restaurantes")
        val restaurants = homeData.getRestaurantList()
        restaurantsLiveData.postValue(restaurants)
    }
}