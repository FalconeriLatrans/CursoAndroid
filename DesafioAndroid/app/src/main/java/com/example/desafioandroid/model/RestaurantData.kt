package com.example.desafioandroid.model

import android.util.Log

class RestaurantData {
    fun getRestaurantList():List<Restaurant>{
        Log.i("Tela 1", "Pegando lista de restaurantes")
        return listOf(
            Restaurant(1,"Tony Roma's","Av. Lavandisca, 717 - Indianópolis, São Paulo","22:00","https://www.guiadasemana.com.br/contentFiles/image/2017/09/VEN/thumbnail/96245_w380h235_1506303457tonyromas.jpg"),
            Restaurant(2,"Aoyama - Moema","Alameda dos Arapanés, 532 - Moema","00:00","https://vejasp.abril.com.br/wp-content/uploads/2016/11/aoyama_jairmagri.jpeg?quality=70&strip=info&resize=680,453"),
            Restaurant(3,"Outback - Moema","Av. Moaci, 187, 187 - Moema, São Paulo","00:00","https://vejasp.abril.com.br/wp-content/uploads/2016/11/ribsonthebarbie-outback-5.jpeg?quality=70&strip=info&w=916"),
            Restaurant(4,"Sí Señor!","Alameda Jauaperi, 626 - Moema","01:00","https://sisenor.com.br/wp-content/uploads/2020/03/nachos-supreme-1-2048x2048.jpg")
        )
    }
    fun getDishListById(id:Int?):List<Dish>{
        if (id==2){
            return listOf(
                Dish("Nigiri","https://guiafeminina.com.br/wp-content/uploads/2015/09/Nigiri.jpg"),
                Dish("Norimaki","https://guiafeminina.com.br/wp-content/uploads/2015/09/Norimaki.jpg"),
                Dish("Temaki","https://guiafeminina.com.br/wp-content/uploads/2015/09/temaki.jpg"),
                Dish("Sashimi","https://guiafeminina.com.br/wp-content/uploads/2015/09/sashimi.jpg"),
                Dish("Hot Roll","https://guiafeminina.com.br/wp-content/uploads/2016/08/Hot-Salmao1.jpg"),
                Dish("Joe Salmão","http://www.nirusushi.com.br/images/43.jpg")
            )
        } else return listOf(
            Dish("Baião de Dois","https://oimparcial.com.br/app/uploads/2020/06/baiao-de-dois-1280x720-1-1024x576.jpg"),
            Dish("Frango à Milanesa","https://www.saboresajinomoto.com.br/uploads/images/recipes/frango-a-milanesa.jpg"),
            Dish("Feijoada","https://www.comerciosnobairro.com.br/anunciante/img/img_produtos/20171114120227_799bad5a3b514f096e69bbc4a7896cd9_1960217452_img_produtos.jpg"),
            Dish("Picanha ao Alho","https://images.rappi.com.br/products/310204bd-211c-40d0-befb-4407fe0ddc4c-1589922210145.png?d=128x90")
        )


    }
}