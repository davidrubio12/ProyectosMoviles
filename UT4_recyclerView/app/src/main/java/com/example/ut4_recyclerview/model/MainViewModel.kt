package com.example.ut4_recyclerview.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ut4_recyclerview.Color
import kotlinx.coroutines.launch


class   MainViewModel : ViewModel() {
    private val _datos = MutableLiveData<List<Color>>(emptyList())
    val datos: LiveData<List<Color>> get() = _datos
    val myEstado = MainState()

    private val _delete : MutableLiveData<MyData> = MutableLiveData<MyData>()
    private val _add : MutableLiveData<MyData> = MutableLiveData<MyData>()

    val delete : LiveData<MyData> get() = _delete
    val add : LiveData<MyData> get() = _add

    fun delete(position: Int) {
        viewModelScope.launch {
            _delete.value = myEstado.delete(position)
        }
    }

    fun add(position: Int,color: Color) {
        viewModelScope.launch {
            _add.value = myEstado.add(position,color)
        }
    }

    fun devuelveArray() {
        viewModelScope.launch {
            var retonoDatos = myEstado.devuelveColores()
            _datos.value = retonoDatos
        }


    }


}