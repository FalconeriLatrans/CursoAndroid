package com.example.desafioandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainAdapter(
    private val restaurantsList: List<Restaurant>,
    private val itemClicked: (Int) -> Unit
): RecyclerView.Adapter<MainAdapter.LocalViewHolder>() {


    inner class LocalViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        fun bind(restaurant: Restaurant) = with(itemView){

            val imageView = findViewById<ImageView>(R.id.mc_RestImage)
            Glide.with(this).load(restaurant.imagesrc).into(imageView)

            findViewById<TextView>(R.id.mc_RestName).text = restaurant.name
            findViewById<TextView>(R.id.mc_RestAdress).text = restaurant.adress
            findViewById<TextView>(R.id.mc_RestHour).text = context.getString(R.string.mc_hour,restaurant.hour)

            findViewById<ConstraintLayout>(R.id.mc_background).setOnClickListener {
                itemClicked(this@LocalViewHolder.adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.main_card,
            parent,
            false
        )
        return LocalViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        holder.bind(restaurantsList[position])
    }

    override fun getItemCount(): Int {
        return restaurantsList.size
    }

}

