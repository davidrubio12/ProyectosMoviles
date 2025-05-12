package com.example.proyectodemoviles.ui.cart

import CartAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.viewmodel.CartViewModel

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var viewModel: CartViewModel
    private lateinit var totalTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        viewModel = ViewModelProvider(this)[CartViewModel::class.java]

        recyclerView = view.findViewById(R.id.recyclerCart)
        totalTextView = view.findViewById(R.id.totalCarrito)
        recyclerView.layoutManager = LinearLayoutManager(context)


        adapter = CartAdapter(
            emptyList(),
            onSumar = { item -> viewModel.añadirProducto(item.productId, 1) },
            onRestar = { item -> viewModel.añadirProducto(item.productId, -1) },
            onEliminar = { item -> viewModel.eliminarProducto(item.productId) }
        )
        recyclerView.adapter = adapter

        viewModel.cart.observe(viewLifecycleOwner) { cart ->
            cart?.let {
                adapter.updateData(it.items)
                totalTextView.text = "Total: €%.2f".format(it.totalPrice)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.cargarCarrito()

        return view
    }
}