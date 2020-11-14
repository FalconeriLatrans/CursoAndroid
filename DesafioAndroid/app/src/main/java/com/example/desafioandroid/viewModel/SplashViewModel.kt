package com.example.desafioandroid.viewModel

import com.example.desafioandroid.model.AppResources
import com.example.desafioandroid.model.RestaurantData

class SplashViewModel() {
    var imageSrc: String = ""
    private val appResources = AppResources()

    init {getImage()}

    fun getImage(){imageSrc = appResources.splashimage}

}