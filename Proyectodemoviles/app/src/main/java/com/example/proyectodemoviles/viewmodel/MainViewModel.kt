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



    var paginaActual = 0
    private var totalPaginas = 1
    var cargaInicial = true
    private var isLoading = false

    private val _datosCargados = mutableListOf<ProductDto>()

    fun cargarProductos(search: String = "", categoriaId: Long? = null, reset: Boolean = false) {
        if (isLoading) return
        isLoading = true

        if (reset) {
            paginaActual = 0
            totalPaginas = 1
            cargaInicial = true
            _datosCargados.clear()
            _productos.value = emptyList()
        }

        viewModelScope.launch {
            try {
                val response = repository.getProductosPaginados(paginaActual, search, categoriaId)

                if (response.isSuccessful) {
                    val page = response.body()
                    totalPaginas = page?.totalPages ?: 1
                    val nuevos = page?.content ?: emptyList()

                    _datosCargados.addAll(nuevos)
                    _productos.value = nuevos
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Error de red: ${e.localizedMessage}"
            }

            isLoading = false
        }
    }



    fun cargarSiguientePagina(categoriaId: Long? = null) {
        if (paginaActual + 1 < totalPaginas) {
            paginaActual++
            cargarProductos(categoriaId = categoriaId, reset = false)
        }
    }
}