package com.example.desafioandroid.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
class Restaurant (
    val id: Int,
    val name: String,
    val adress: String,
    val hour: String,
    val imagesrc: String
): Parcelable