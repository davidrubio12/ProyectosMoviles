package com.example.recyclerview.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val _datos = MutableLiveData<List<String>>(emptyList())
    val datos: LiveData<List<String>> get() = _datos
    val myEstado = MainState()

     val _delete : MutableLiveData<MyData> = MutableLiveData<MyData>()

    private val _add : MutableLiveData<MyData> = MutableLiveData<MyData>()

    val delete : LiveData<MyData> get() = _delete
    val add : LiveData<MyData> get() = _add

    fun devuelveArray() {
        viewModelScope.launch {
            var retonoDatos = myEstado.devuelveAnimales()
            _datos.value = retonoDatos
        }


    }

    fun delete(position:Int){
        viewModelScope.launch {
        _delete.value = myEstado.delete(position)
        }
    }
    fun add(position:Int,nombre:String){
        viewModelScope.launch {
            _add.value = myEstado.add(position,nombre)

        }
    }


}