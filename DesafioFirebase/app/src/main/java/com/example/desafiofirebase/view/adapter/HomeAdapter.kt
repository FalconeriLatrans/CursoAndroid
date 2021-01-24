package com.example.desafiofirebase.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiofirebase.R
import com.example.desafiofirebase.util.Game

class HomeAdapter(
    private val gameList: List<Game>,
    private val itemClicked: (Int) -> Unit
): RecyclerView.Adapter<HomeAdapter.LocalViewHolder>() {

    inner class LocalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var gameImage = itemView.findViewById<ImageView>(R.id.card_image)
        var gameTitle = itemView.findViewById<TextView>(R.id.card_title)
        var gameDate = itemView.findViewById<TextView>(R.id.card_date)
        var background = itemView.findViewById<LinearLayout>(R.id.card_background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        Log.i("Tela 1", "Inflando")
        return LocalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_game,parent,false))
    }
    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        Log.i("Tela 1", "Criando view ${position}")
        Glide.with(holder.itemView.context).load(gameList[position].thumbnail).into(holder.gameImage)
        holder.gameTitle.text = "#${gameList[position].name}"
        holder.gameDate.text = "#${gameList[position].created}"
        holder.background.setOnClickListener {
            itemClicked(position)
        }
        Log.i("Tela 1", "View ${position} criada")
    }
    override fun getItemCount(): Int {
        Log.i("Tela 1", "Lista com ${gameList.size} elementos")
        return gameList.size
    }

}