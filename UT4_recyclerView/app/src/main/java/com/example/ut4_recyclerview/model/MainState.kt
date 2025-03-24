package com.example.ut4_recyclerview.model

import com.example.ut4_recyclerview.Color

class MainState {
    var colores = mutableListOf(
        Color("Verde", "#4CAF50"),
        Color("Amarillo", "#FFEB3B"),
        Color("Azul", "#2196F3"),
        Color("Indigo", "#3F51B5"),
        Color("Rojo", "#F44336"),
        Color("Naranja", "#FF6600"),
        Color("Gris", "#757575"),
        Color("Violeta", "#673AB7"),
        Color("blanco", "#FFFFFF"),
        Color("Rosa", "#FF0080"))

    fun devuelveColores(): MutableList<Color> {
        return colores

    }
    fun delete(position:Int):MyData {
        colores.removeAt(position)
        return MyData(position,colores)
    }
    fun add(position:Int,nombre: Color): MyData {
        colores.add(position,nombre)
        return MyData(position, colores)
    }

}