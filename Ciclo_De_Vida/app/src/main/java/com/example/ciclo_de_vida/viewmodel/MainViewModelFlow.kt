package com.example.ciclo_de_vida.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciclo_de_vida.model.Datos
import com.example.ciclo_de_vida.model.MainState
import kotlinx.coroutines.launch

class MainViewModelFlow : ViewModel(){
    private val _datos = MutableLiveData(Datos(0,0,false))
    val  datos : LiveData<Datos> get()= _datos
    val  myEstado = MainState()

    fun sumar(valor:Int,misDatos:Datos){
        viewModelScope.launch {
            var retornoDatos= myEstado.sumar(valor,misDatos)
            _datos.value = retornoDatos
        }
    }
}