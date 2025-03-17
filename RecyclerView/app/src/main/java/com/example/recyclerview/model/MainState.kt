package com.example.recyclerview.model

class MainState {
    var animalNames = mutableListOf("Caballo", "Perro", "Gato", "Raton", "Elefante", "León", "Mono", "Gorila", "Oso", "Canguro", "Tigre", "Zorro", "Serpiente", "Dragon", "Cerdo", "Pavo", "Conejo", "Zor" +
            "illa", "Caimán", "Hipopotamo", "Ceratops", "Lagarto", "Rana", "Aguila", "Araña")

    fun devuelveAnimales(): MutableList<String> {

        return animalNames
    }
    fun delete(position: Int): MyData {
        animalNames.removeAt(position)
        return MyData(position,animalNames)
    }
    fun add (position: Int,nombre:String): MyData{
        animalNames.add(position,nombre)
        return MyData(position,animalNames)
    }
}
