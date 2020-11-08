package com.example.desafioandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RestaurantDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)


        val dishes:List<Dish> = listOf(
                Dish("Baião de Dois","https://www.guiadasemana.com.br/contentFiles/image/2017/09/VEN/thumbnail/96245_w380h235_1506303457tonyromas.jpg"),
                Dish("Frango à Milanesa","https://vejasp.abril.com.br/wp-content/uploads/2016/11/aoyama_jairmagri.jpeg?quality=70&strip=info&resize=680,453"),
                Dish("Feijoada","https://vejasp.abril.com.br/wp-content/uploads/2016/11/ribsonthebarbie-outback-5.jpeg?quality=70&strip=info&w=916"),
                Dish("Picanha ao Alho","https://sisenor.com.br/wp-content/uploads/2020/03/nachos-supreme-1-2048x2048.jpg"),
        )
        findViewById<RecyclerView>(R.id.rd_Container).apply {
            layoutManager = GridLayoutManager(this@RestaurantDetails,2)
            adapter = RdAdapter(dishes)
        }
    }
}