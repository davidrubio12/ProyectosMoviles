package com.example.recyclerview.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _datos = MutableLiveData<List<String>>(emptyList())
    val datos: LiveData<List<String>> get() = _datos
    val myEstado = MainState()

    fun devuelveArray() {
        viewModelScope.launch {
            var retonoDatos = myEstado.devuelveAnimales()
            _datos.value = retonoDatos
        }


    }


}