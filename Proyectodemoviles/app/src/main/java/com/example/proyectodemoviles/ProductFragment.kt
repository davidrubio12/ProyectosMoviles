package com.example.proyectodemoviles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.data.TokenManager
import com.example.proyectodemoviles.model.MainViewModel
import com.example.proyectodemoviles.model.MainViewModelFactory
import com.example.proyectodemoviles.recycler.MyAdapter
import kotlin.jvm.java

class ProductFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var spinner: Spinner

    private val categorias = mapOf(
        "Todo" to null,
        "Cartas sueltas" to 1L,
        "Sobres" to 2L,
        "Accesorios" to 3L
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        recyclerView = view.findViewById(R.id.recyclerProductos)
        recyclerView.layoutManager = LinearLayoutManager(context)

        spinner = view.findViewById(R.id.spinnerCategorias)
        spinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categorias.keys.toList()
        )


        val tokenManager = TokenManager(requireContext())
//        viewModel = ViewModelProvider(this, MainViewModelFactory(tokenManager)).get(MainViewModel::class.java)

        viewModel.productos.observe(viewLifecycleOwner) { productos ->
            adapter = MyAdapter(productos) { producto ->
                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra("url_imagen", producto.imageUrl)
                intent.putExtra("nombre_producto", producto.name)
                intent.putExtra("precio_producto", producto.price)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.cargarProductos()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val nombreCategoria = categorias.keys.toList()[position]
                val idCategoria = categorias[nombreCategoria]
                viewModel.cargarProductos(categoriaId = idCategoria)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }



        return view
    }
}
