package com.example.desafiowebservices.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiowebservices.model.Comics.Comics
import com.example.desafiowebservices.model.Comics.Result
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel: ViewModel() {

    var homeComicsList: MutableLiveData<List<Result>> = MutableLiveData()

    fun getHomeComics() {
        val tempComicsList: MutableList<Result> = mutableListOf()
        viewModelScope.launch {
            when (val response = getComicsBySeriesID(24396)) {
                is ResponseApi.Success -> {
                    val data = response.data as Comics
                    tempComicsList.addAll(data.data.results)
                }
                is ResponseApi.Error -> {
                }
            }
            homeComicsList.postValue(tempComicsList)
        }
    }

    private suspend fun getComicsBySeriesID(ID: Int): ResponseApi {
        return try {
            val response = ApiService.marvelApi.ComicsBySeriesID(ID)

            if (response.isSuccessful) {
                ResponseApi.Success(response.body())
            } else {
                if (response.code() == 404) {
                    ResponseApi.Error("Dado não encontrado")
                } else {
                    ResponseApi.Error("Erro ao carregar os dados")
                }
            }
        } catch (exception: Exception) {
            ResponseApi.Error("Erro ao carregar os dados")
        }
    }
}