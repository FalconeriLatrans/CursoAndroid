package com.example.exercicioaula27

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    private val userList: List<User>,
    private val itemClicked: (Int) -> Unit
): RecyclerView.Adapter<MainAdapter.LocalViewHolder>() {

    inner class LocalViewHolder(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        fun bind(user:User) = with(itemView){
            findViewById<TextView>(R.id.PersonName).text = user.personName
            findViewById<TextView>(R.id.LastMessage).text = user.LastMessage
            findViewById<TextView>(R.id.HourLastMessage).text = user.HourLastMessage
            findViewById<TextView>(R.id.QtyMessages).text = user.QtyMessages.toString()

            findViewById<ConstraintLayout>(R.id.rvBackground).setOnClickListener {
                itemClicked(this@LocalViewHolder.adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.whatsapp_recycler_view, parent, false)
        return LocalViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}
@Parcelize
data class User (
    val personName: String,
    val LastMessage: String,
    val HourLastMessage: String,
    val QtyMessages: Int,
) : Parcelable {
}