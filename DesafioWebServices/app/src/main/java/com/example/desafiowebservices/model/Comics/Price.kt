package com.example.desafiowebservices.model.Comics

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    val price: Double,
    val type: String
): Parcelable {
    fun getPrice(): String{
        return "$ ${price}"
    }
}