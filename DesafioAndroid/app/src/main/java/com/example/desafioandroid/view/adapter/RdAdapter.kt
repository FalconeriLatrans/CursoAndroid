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
import com.example.desafioandroid.model.Dish
import com.google.android.material.card.MaterialCardView

class RdAdapter(
        private val dishesList: List<Dish>,
        private val itemClicked: (Int) -> Unit
): RecyclerView.Adapter<RdAdapter.LocalViewHolder>() {

    inner class LocalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var dishImage = itemView.findViewById<ImageView>(R.id.dishcard_image)
        var dishName = itemView.findViewById<TextView>(R.id.dishcard_name)
        var background = itemView.findViewById<MaterialCardView>(R.id.rd_card)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        Log.i("Tela 2", "Inflando")
        return LocalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_dish, parent, false));
    }
    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        Log.i("Tela 2", "Criando view ${position}")
        Glide.with(holder.itemView.context).load(dishesList[position].imagesrc).into(holder.dishImage)
        holder.dishName.text = dishesList[position].name
        holder.background.setOnClickListener {
            itemClicked(position)
        }
        Log.i("Tela 2", "View ${position} criada")
    }
    override fun getItemCount(): Int {
        Log.i("Tela 2", "Lista com ${dishesList.size} elementos")
        return dishesList.size
    }
}