package com.example.ut4_recyclerview.model

import com.example.ut4_recyclerview.Color

class MainState {
    fun devuelveColores(): MutableList<Color> {
        return mutableListOf(
            Color("Verde", "#4CAF50"),
            Color("Amarillo", "#FFEB3B"),
            Color("Azul", "#2196F3"),
            Color("Indigo", "#3F51B5"),
            Color("Rojo", "#F44336"),
            Color("Naranja", "#FF6600"),
            Color("Gris", "#757575"),
            Color("Violeta", "#673AB7"),
            Color("blanco", "#FFFFFF"),
            Color("Rosa", "#FF0080"),
        )
    }
}