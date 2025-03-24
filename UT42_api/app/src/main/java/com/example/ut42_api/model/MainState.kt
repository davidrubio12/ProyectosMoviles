package com.example.ut42_api.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainState {
    var cadena = "https://dog.ceo/api/breed/"

    suspend fun recuperaFotos(raza:String): DogRespuesta {
        val cadenaFinal = cadena +raza+"/"
        val retrofit = Retrofit.Builder()
            .baseUrl(cadenaFinal)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call = retrofit.create(DogAPIService::class.java).getFotosPerros()
        val fotosPerros = call.body()
        if(fotosPerros!=null){
            return fotosPerros
        }else{
            return DogRespuesta("no success",null)
        }
    }
}