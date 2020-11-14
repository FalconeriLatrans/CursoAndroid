package com.example.desafioandroid.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafioandroid.model.Dish
import com.example.desafioandroid.model.Restaurant
import com.example.desafioandroid.model.RestaurantData

class RestaurantViewModel : ViewModel(){
    val dishesLiveData: MutableLiveData<List<Dish>> = MutableLiveData()
    private val homeBusiness = RestaurantData()

    fun getDishes(id: Int?) {
        val dishes = homeBusiness.getDishListById(id)
        dishesLiveData.postValue(dishes)
    }
}