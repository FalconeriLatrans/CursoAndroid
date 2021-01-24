package com.example.desafiofirebase.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiofirebase.util.Game
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel: ViewModel() {

    var homeComicsList: MutableLiveData<List<Game>> = MutableLiveData()

    fun getHomeComics() {
//        val tempComicsList: MutableList<Result> = mutableListOf()
//        viewModelScope.launch {
//            when (val response = getComicsBySeriesID(24396)) {
//                is ResponseApi.Success -> {
//                    val data = response.data as Comics
//                    tempComicsList.addAll(data.data.results)
//                }
//                is ResponseApi.Error -> {
//                }
//            }
//            homeComicsList.postValue(tempComicsList)
//        }
    }
}