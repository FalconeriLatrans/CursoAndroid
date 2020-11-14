package com.example.desafioandroid.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafioandroid.R
import com.example.desafioandroid.model.Restaurant
import com.google.android.material.card.MaterialCardView

class MainAdapter(
        private val restaurantsList: List<Restaurant>,
        private val itemClicked: (Int) -> Unit
): RecyclerView.Adapter<MainAdapter.LocalViewHolder>() {

    inner class LocalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var restImage = itemView.findViewById<ImageView>(R.id.mc_RestImage)
        var restName = itemView.findViewById<TextView>(R.id.mc_RestName)
        var restAdress = itemView.findViewById<TextView>(R.id.mc_RestAdress)
        var restHour = itemView.findViewById<TextView>(R.id.mc_RestHour)
        var background = itemView.findViewById<MaterialCardView>(R.id.main_card)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        Log.i("Tela 1", "Inflando")
        return LocalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_restaurant,parent,false))
    }
    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        Log.i("Tela 1", "Criando view ${position}")
        Glide.with(holder.itemView.context).load(restaurantsList[position].imagesrc).into(holder.restImage)
        holder.restName.text = restaurantsList[position].name
        holder.restAdress.text = restaurantsList[position].adress
        holder.restHour.text = holder.itemView.context.getString(R.string.mc_hour,restaurantsList[position].hour)
        holder.background.setOnClickListener {
            itemClicked(position)
        }
        Log.i("Tela 1", "View ${position} criada")
    }
    override fun getItemCount(): Int {
        Log.i("Tela 1", "Lista com ${restaurantsList.size} elementos")
        return restaurantsList.size
    }

}

