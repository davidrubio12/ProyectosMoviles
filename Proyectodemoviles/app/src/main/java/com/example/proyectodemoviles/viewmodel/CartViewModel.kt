package com.example.proyectodemoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodemoviles.model.dto.CartDto
import com.example.proyectodemoviles.repository.CartRepository

import kotlinx.coroutines.launch

class CartViewModel() : ViewModel() {

    private val cartRepository = CartRepository()


    private val _cart = MutableLiveData<CartDto>()
    val cart: LiveData<CartDto> get() = _cart

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun cargarCarrito() {
        viewModelScope.launch {
            try {
                val response = cartRepository.getCart()
                _cart.value = if (response.isSuccessful) response.body() else null
            } catch (e: Exception) {
                _error.value = "Error al cargar carrito: ${e.localizedMessage}"
            }
        }
    }
    fun añadirProducto(productId: Long, cantidad: Int) {
        viewModelScope.launch {
            try {
                val response = cartRepository.addToCart(productId, cantidad)
                if (response.isSuccessful) {
                    _cart.value = response.body()
                }
            } catch (e: Exception) {
                _error.value = "Error al añadir al carrito"
            }
        }
    }

    fun eliminarProducto(productId: Long) {
        viewModelScope.launch {
            try {
                val response = cartRepository.removeFromCart(productId)
                if (response.isSuccessful) {
                    _cart.value = response.body()
                }
            } catch (e: Exception) {
                _error.value = "Error al eliminar del carrito"
            }
        }
    }
}