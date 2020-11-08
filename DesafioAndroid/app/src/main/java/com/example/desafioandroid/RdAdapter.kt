package com.example.desafioandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RdAdapter(
        private val dishesList: List<Dish>,
): RecyclerView.Adapter<RdAdapter.LocalViewHolder>() {


    inner class LocalViewHolder(
            itemView: View
    ): RecyclerView.ViewHolder(itemView){
        fun bind(dish: Dish) = with(itemView){

            val imageView = findViewById<ImageView>(R.id.dishcard_image)
            Glide.with(this).load(dish.imagesrc).into(imageView)

            findViewById<TextView>(R.id.dishcard_name).text = dish.name
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
        holder.bind(dishesList[position])
    }

    override fun getItemCount(): Int {
        return dishesList.size
    }
}