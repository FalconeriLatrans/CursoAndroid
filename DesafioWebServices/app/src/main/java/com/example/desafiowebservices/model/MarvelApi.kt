package com.example.desafiowebservices.model

import com.example.desafiowebservices.model.Comics.Comics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
        //Spider-Man            ID 1009610
        //The Amazing Spider Man (2018)  ID 24396

        //https://gateway.marvel.com:443/v1/public/characters/1009610/comics?apikey=c32125ee0eec7c5e61969691516f131c
        @GET("characters/{characterId}/comics")
        suspend fun ComicsByCharsID(@Path("characterId") charID: Int): Response<Comics>

        //https://gateway.marvel.com:443/v1/public/series/24396/comics?noVariants=true&limit=10&apikey=c32125ee0eec7c5e61969691516f131c
        @GET("series/{seriesId}/comics")
        suspend fun ComicsBySeriesID(@Path("seriesId") charID: Int, @Query("limit")limit: Int = 20, @Query("offset")offset: Int = 0, @Query("noVariants")noVar: Boolean = true, @Query("orderBy")order: String = "issueNumber"): Response<Comics>
}