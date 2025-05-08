package com.example.proyectodemoviles.util

object CartList {
    val productosCarrito = mutableListOf<Cart>() // Lista de productos con cantidad

    fun agregarProducto(producto: Cart) {
        productosCarrito.add(producto)
    }
    fun obtenerProductos(): List<Cart> {
        return productosCarrito
    }
}