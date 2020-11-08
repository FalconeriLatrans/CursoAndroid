package com.example.desafioandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var restaurants:List<Restaurant> = listOf(
                Restaurant("Tony Roma's","Av. Lavandisca, 717 - Indianópolis, São Paulo","22:00","https://www.guiadasemana.com.br/contentFiles/image/2017/09/VEN/thumbnail/96245_w380h235_1506303457tonyromas.jpg"),
                Restaurant("Aoyama - Moema","Alameda dos Arapanés, 532 - Moema","00:00","https://vejasp.abril.com.br/wp-content/uploads/2016/11/aoyama_jairmagri.jpeg?quality=70&strip=info&resize=680,453"),
                Restaurant("Outback - Moema","Av. Moaci, 187, 187 - Moema, São Paulo","00:00","https://vejasp.abril.com.br/wp-content/uploads/2016/11/ribsonthebarbie-outback-5.jpeg?quality=70&strip=info&w=916"),
                Restaurant("Sí Señor!","Alameda Jauaperi, 626 - Moema","01:00","https://sisenor.com.br/wp-content/uploads/2020/03/nachos-supreme-1-2048x2048.jpg"),
        )
        findViewById<RecyclerView>(R.id.Main_Container).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(restaurants){position ->
                val intent = Intent(this@MainActivity, R.layout.activity_restaurant_details::class.java)
                intent.putExtra(KEY_INTENT_USER, restaurants[position])
                startActivity(intent)
            }
        }
    }
    companion object {
        const val KEY_INTENT_USER = "restaurant"
    }
}