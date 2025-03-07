package com.example.recyclerview.model

class MainState {

    fun devuelveAnimales(): MutableList<String> {
        var animalNames = mutableListOf("Caballo", "Perro", "Gato", "Raton", "Elefante", "León", "Mono", "Gorila", "Oso", "Canguro", "Tigre", "Zorro", "Serpiente", "Dragon", "Cerdo", "Pavo", "Conejo", "Zor" +
                "illa", "Caimán", "Hipopotamo", "Ceratops", "Lagarto", "Rana", "Aguila", "Araña")
        return animalNames
    }
}