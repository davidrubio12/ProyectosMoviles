package com.example.proyectodemoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodemoviles.model.dto.CategoryDto
import com.example.proyectodemoviles.repository.CategoryRepository
import kotlinx.coroutines.launch


class CategoryViewModel : ViewModel() {
    private val repository = CategoryRepository()

    private val _categorias = MutableLiveData<List<CategoryDto>>()
    val categorias: LiveData<List<CategoryDto>> get() = _categorias

    fun cargarCategorias() {
        viewModelScope.launch {
            val response = repository.getAllCategories()
            if (response.isSuccessful) {
                _categorias.value = response.body()
            }
        }
    }

    fun getCategoriaIdPorNombre(nombre: String): Long? {
        return if (nombre == "Todo") null
        else _categorias.value?.firstOrNull { it.name == nombre }?.id
    }
}