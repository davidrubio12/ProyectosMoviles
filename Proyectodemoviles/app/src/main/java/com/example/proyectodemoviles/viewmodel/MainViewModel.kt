package com.example.proyectodemoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodemoviles.model.dto.ProductDto
import com.example.proyectodemoviles.repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val repository = ProductRepository()

    private val _productos = MutableLiveData<List<ProductDto>>()
    val productos: LiveData<List<ProductDto>> get() = _productos

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val categorias = mapOf(
        "Todo" to null,
        "Cartas sueltas" to 1L,
        "Sobres" to 2L,
        "Accesorios" to 3L
    )

    fun cargarProductos(search: String = "", categoriaId: Long? = null) {
        viewModelScope.launch {
            try {
                val response = repository.getAllProducts(search, categoriaId)
                if (response.isSuccessful) {
                    _productos.value = response.body()?.content ?: emptyList()
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Error de red: ${e.localizedMessage}"
            }
        }
    }

    fun getNombreCategorias(): List<String> = categorias.keys.toList()

    fun getCategoriaIdPorPosicion(position: Int): Long? =
        categorias[getNombreCategorias()[position]]
}