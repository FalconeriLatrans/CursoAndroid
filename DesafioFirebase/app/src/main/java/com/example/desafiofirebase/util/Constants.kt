package com.example.desafiofirebase.util

class Constants {

    object Intent {
        const val KEY_INTENT_GAME = "game"
        const val KEY_INTENT_EMAIL = "email"
    }
    object SharedPreferences {
        const val KEY_SP_DBNAME = "sp_desafio_firebase"
        const val KEY_SP_LOGIN_REMEMBER = "sp_desafio_firebase"
    }
    object Codes {
        const val IMAGE_PICK_CODE = 1000
        const val SPEECH_REQUEST_CODE = 2000
    }
    object Database {
        const val DATABASE_NAME = "games"
        const val DB_CREATED_BY = "createdBy"
        const val DB_TITLE = "title"
        const val DB_ID = "id"
        const val DB_THUMBNAIL = "thumbnail"
        const val DB_RELEASE = "release"
        const val DB_DESCRIPTION = "description"
    }
}