package com.example.desafiowebservices.model.Comics

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Parcelize
data class Date(
    val date: String,
    val type: String
): Parcelable {
    fun getDateAsString(): String{
        val format = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
        val dateAsDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"))
        return dateAsDate.format(format)
    }
}