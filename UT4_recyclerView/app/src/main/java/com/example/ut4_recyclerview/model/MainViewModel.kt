package com.example.ut4_recyclerview.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ut4_recyclerview.Color
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val _datos = MutableLiveData<List<Color>>(emptyList())
    val datos: LiveData<List<Color>> get() = _datos
    val myEstado = MainState()

    fun devuelveArray() {
        viewModelScope.launch {
            var retonoDatos = myEstado.devuelveColores()
            _datos.value = retonoDatos
        }


    }
    fun agregarColor(position:Int,color: Color) {
        viewModelScope.launch {
            myEstado.add(position,color)
            _datos.value = myEstado.devuelveColores()
        }
    }
    fun eliminarColor(position: Int) {
        viewModelScope.launch {
            myEstado.delete(position)
            _datos.value = myEstado.devuelveColores()
        }
    }


}