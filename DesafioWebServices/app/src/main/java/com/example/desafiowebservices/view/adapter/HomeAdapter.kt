package com.example.desafiowebservices.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservices.model.Comics.Result
import com.bumptech.glide.Glide
import com.example.desafiowebservices.R
import com.google.android.material.card.MaterialCardView

class HomeAdapter(
    private val comicsList: List<Result>,
    private val itemClicked: (Int) -> Unit
): RecyclerView.Adapter<HomeAdapter.LocalViewHolder>() {

    inner class LocalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var hqImage = itemView.findViewById<ImageView>(R.id.card_image)
        var hqNumber = itemView.findViewById<TextView>(R.id.card_number)
        var background = itemView.findViewById<LinearLayout>(R.id.card_background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        Log.i("Tela 1", "Inflando")
        return LocalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_hq,parent,false))
    }
    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        Log.i("Tela 1", "Criando view ${position}")
        Glide.with(holder.itemView.context).load(comicsList[position].thumbnail.getThumb()).into(holder.hqImage)
        holder.hqNumber.text = "#${comicsList[position].issueNumber}"
        holder.background.setOnClickListener {
            itemClicked(position)
        }
        Log.i("Tela 1", "View ${position} criada")
    }
    override fun getItemCount(): Int {
        Log.i("Tela 1", "Lista com ${comicsList.size} elementos")
        return comicsList.size
    }

}