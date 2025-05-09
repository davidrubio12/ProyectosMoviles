package com.example.proyectodemoviles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.data.TokenManager
import com.example.proyectodemoviles.model.CartViewModel

import com.example.proyectodemoviles.recycler.CartAdapter
import com.example.proyectodemoviles.util.Cart
import com.example.proyectodemoviles.util.CartList

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.recyclerCart)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val tokenManager = TokenManager(requireContext())
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CartViewModel(tokenManager) as T
            }
        }).get(CartViewModel::class.java)

        viewModel.cart.observe(viewLifecycleOwner) { carrito ->
            adapter = CartAdapter(
                carrito.items,
                onDeleteClick = { item -> viewModel.eliminarItem(item.productId) },
                onSumarClick = { item -> viewModel.modificarCantidad(item.productId, item.quantity + 1) },
                onRestarClick = { item ->
                    if (item.quantity > 1) {
                        viewModel.modificarCantidad(item.productId, item.quantity - 1)
                    } else {
                        viewModel.eliminarItem(item.productId)
                    }
                }
            )
            recyclerView.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.cargarCarrito()

        return view
    }
}
