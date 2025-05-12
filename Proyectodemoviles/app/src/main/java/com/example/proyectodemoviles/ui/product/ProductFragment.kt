package com.example.proyectodemoviles.ui.product

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
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.recycler.MyAdapter
import com.example.proyectodemoviles.viewmodel.MainViewModel
import com.example.proyectodemoviles.ui.product.ProductDetailActivity

class ProductFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        // ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // RecyclerView
        recyclerView = view.findViewById(R.id.recyclerProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyAdapter(emptyList()) { producto ->
            val intent = Intent(requireContext(), ProductDetailActivity::class.java).apply {
                putExtra("producto_id", producto.id)
                putExtra("nombre_producto", producto.name)
                putExtra("precio_producto", producto.price)
                putExtra("url_imagen", producto.imageUrl)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Spinner
        spinner = view.findViewById(R.id.spinnerCategorias)
        val categorias = viewModel.getNombreCategorias()
        spinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categorias
        )

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val categoriaId = viewModel.getCategoriaIdPorPosicion(position)
                viewModel.cargarProductos(categoriaId = categoriaId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Observers
        viewModel.productos.observe(viewLifecycleOwner) { productos ->
            adapter.updateData(productos)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        // Carga inicial
        viewModel.cargarProductos()

        return view
    }
}
