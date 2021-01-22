package com.example.desafiowebservices.util

class Constants {

    object Api {
        const val BASE_MARVEL_URL = "https://gateway.marvel.com:443/v1/public/"
        const val PUBLIC_KEY = "c32125ee0eec7c5e61969691516f131c"
        const val MARVEL_KEY = "a06ba3db59e199db547c0cc9a65e173acf77419e"
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
        const val API_TS_NAME = "ts"
        const val API_HASH_NAME = "hash"
        const val BASE_URL_ORIGINAL_IMAGE = "http://i.annihil.us/u/prod/marvel/i/mg/3/50/"
        const val API_KEY_NAME = "apikey"
        const val API_KEY_VALUE = "c32125ee0eec7c5e61969691516f131c"
        const val API_CONTENT_TYPE_NAME = "Content-Type"
        const val API_CONTENT_TYPE_VALUE = "application/json;charset=utf-8"
        const val QUERY_PARAM_LANGUAGE_LABEL = "language"
        const val QUERY_PARAM_LANGUAGE_VALUE = "pt-BR"
        const val KEY_ACTIVE_SEARCH = "activeSearch"
        const val KEY_INTENT_SEARCH = "search"

        const val KEY_INTENT_COMIC = "comic"
        const val KEY_INTENT_COMIC_IMAGE = "comic_image"

        const val KEY_INTENT_EMAIL = "email"
        const val KEY_INTENT_PASSWORD = "password"
        const val FROM_REGISTER = 0
    }
}