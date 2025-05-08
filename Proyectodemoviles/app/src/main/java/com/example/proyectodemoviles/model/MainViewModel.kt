package com.example.proyectodemoviles.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodemoviles.util.Foto
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val myEstado = MainState()
    private val _datos = MutableLiveData<List<Foto>>(emptyList())

    val datos: LiveData<List<Foto>> get() = _datos

    fun devuelveFotos(raza: String) {
        viewModelScope.launch {
            val respuesta = myEstado.recuperaFotos(raza)
            val listaFotos = respuesta.message?.map { url ->
                Foto(
                    id = url.hashCode().toString(), // puedes usar UUID.randomUUID().toString()
                    url = url,
                    title = "Producto $raza",
                    price = 10.0 // o un valor aleatorio si prefieres
                )
            } ?: emptyList()
            _datos.value = listaFotos
        }
    }

}