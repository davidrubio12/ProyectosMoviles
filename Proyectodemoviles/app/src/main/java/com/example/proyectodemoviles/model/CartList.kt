package com.example.proyectodemoviles.model

object CartList {
    val productosCarrito = mutableListOf<Cart>() // Lista de productos con cantidad

    fun agregarProducto(producto: Cart) {
        productosCarrito.add(producto)
    }
    fun obtenerProductos(): List<Cart> {
        return productosCarrito
    }
}