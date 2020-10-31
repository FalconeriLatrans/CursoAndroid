package com.example.exercicioaula27

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    val userlist: List<User>
): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        fun bind(user:User) = with(itemView){
            findViewById<TextView>(R.id.PersonName).text = user.personName
            findViewById<TextView>(R.id.LastMessage).text = user.LastMessage
            findViewById<TextView>(R.id.HourLastMessage).text = user.HourLastMessage
            findViewById<TextView>(R.id.QtyMessages).text = user.QtyMessages.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.whatsapp_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userlist[position])
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

}

class User(
    val personName: String,
    val LastMessage: String,
    val HourLastMessage: String,
    val QtyMessages: Int,
){

}