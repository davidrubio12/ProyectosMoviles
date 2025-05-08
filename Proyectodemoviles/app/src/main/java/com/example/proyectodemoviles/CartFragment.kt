package com.example.proyectodemoviles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.proyectodemoviles.recycler.CartAdapter
import com.example.proyectodemoviles.util.Cart
import com.example.proyectodemoviles.util.CartList

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private var carritoList: MutableList<Cart> = mutableListOf()  // Lista de productos en el carrito

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.recyclerCart)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Aquí puedes obtener los productos del carrito, ya sea de un ViewModel o de una clase Singleton
        // Ejemplo usando ViewModel (ver siguiente sección)

        // Simulamos algunos productos en el carrito
        carritoList = CartList.obtenerProductos().toMutableList()  // Convertimos la lista de Cart a MutableList para poder modificarla

        // Configuramos el RecyclerView con el adapter
        adapter = CartAdapter(carritoList)
        recyclerView.adapter = adapter

        return view
    }
    // Opcionalmente, podrías implementar métodos para agregar, eliminar o actualizar productos en el carrito
}
