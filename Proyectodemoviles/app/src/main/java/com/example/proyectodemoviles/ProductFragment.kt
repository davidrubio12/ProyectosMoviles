package com.example.proyectodemoviles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.model.MainViewModel
import com.example.proyectodemoviles.recycler.MyAdapter
import kotlin.jvm.java

class ProductFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private val razas = listOf("Selecciona una raza", "hound", "retriever", "bulldog", "poodle")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        val spinner = view.findViewById<Spinner>(R.id.spinnerRazas)
        recyclerView = view.findViewById(R.id.recyclerProductos)
        recyclerView.layoutManager = LinearLayoutManager(context)
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, razas)
        // Llamada a la API (puedes cambiar "labrador" por otra raza)
//        viewModel.devuelveFotos("labrador")

        viewModel.datos.observe(viewLifecycleOwner) {
            adapter = MyAdapter(it){ foto ->

                val intent = Intent(requireContext(), ProductDetailActivity::class.java)
                intent.putExtra("url_imagen", foto.url)
                intent.putExtra("nombre_producto", foto.title)
                intent.putExtra("precio_producto", foto.price)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) {
                    val razaSeleccionada = razas[position]
                    viewModel.devuelveFotos(razaSeleccionada)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        return view
    }
}
