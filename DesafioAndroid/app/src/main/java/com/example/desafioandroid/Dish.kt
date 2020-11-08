package com.example.desafioandroid

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Dish (
        val name: String,
        val imagesrc: String
): Parcelable
