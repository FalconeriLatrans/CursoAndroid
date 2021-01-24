package com.example.desafiofirebase.util

import android.os.Parcelable
import java.util.*
import kotlinx.android.parcel.Parcelize

@Parcelize
class Game (
    val thumbnail: String,
    val name: String,
    val created: Date,
    val description: String
):Parcelable
