package com.example.desafiofirebase.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Game (
    var id: String,
    var thumbnail: String = "",
    val title: String = "",
    val release: String = "",
    val description: String = "",
    val createdBy: String = ""
):Parcelable
