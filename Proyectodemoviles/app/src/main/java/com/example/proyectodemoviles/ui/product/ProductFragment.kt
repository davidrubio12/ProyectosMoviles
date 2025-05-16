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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.recycler.ProductAdapter
import com.example.proyectodemoviles.viewmodel.CategoryViewModel
import com.example.proyectodemoviles.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar



class ProductFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var adapter: ProductAdapter

    //Es un método del ciclo de vida de un Fragment que se llama cuando
    // se va a crear la vista (UI) del fragmento.
//    Parámetro	Significado
//    inflater	Permite "inflar" (convertir) el layout XML en una vista (View)
//    container	Es el ViewGroup padre donde se colocará el fragmento (por ejemplo, el FrameLayout del activity)
//    savedInstanceState	Información previa del estado (si se recrea el fragmento tras rotación, etc.)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        // Inicializamos el ViewModel que contiene la lógica de negocio
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]



        // Configuración del RecyclerView
        recyclerView = view.findViewById(R.id.recyclerProductos)
        val gridColumnCount = 2

        recyclerView.layoutManager = GridLayoutManager(requireContext(), gridColumnCount)

        adapter = ProductAdapter(emptyList()) { producto ->
            // Abrir la actividad de detalle del producto cuando se pulsa
            // Este código crea un intent y luego lo inicia en la actividad ProductDetailActivity
            // Los parámetros extra son pasados a la actividad ProductDetailActivity para mostrar los detalles del producto seleccionado.
            // Esto es una forma de pasar datos entre actividades en Android.
            val intent = Intent(requireContext(), ProductDetailActivity::class.java).apply {
                putExtra("producto_id", producto.id)
                putExtra("nombre_producto", producto.name)
                putExtra("precio_producto", producto.price)
                putExtra("url_imagen", producto.imageUrl)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Añadimos un listener que se activa cada vez que el usuario hace scroll en el RecyclerView

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    // Obtenemos la posición del último item visible en la lista
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val totalItems = adapter.itemCount
                // Si estamos al final de la lista y no es la carga inicial...
                if (lastVisible >= totalItems - 1 && !viewModel.cargaInicial) {
                    Snackbar.make(recyclerView, "Hay más productos disponibles", Snackbar.LENGTH_LONG)
                        .setAction("Cargar") {
                            // Al pulsar "Cargar", obtenemos la categoría seleccionada del spinner
                            val nombreSeleccionado = spinner.selectedItem as String
                            val idCategoria = categoryViewModel.getCategoriaIdPorNombre(nombreSeleccionado)
                            viewModel.cargarSiguientePagina(categoriaId = idCategoria)
                        }.show()
                }
            }
        })

        // Spinner
        // Cargamos las categorías en el spinner
        // Este código es un ejemplo de cómo se pueden cargar datos en un spinner en Kotlin.
        // En una app real, se podría hacer de manera más eficiente utilizando un adaptador personalizado para el spinner.
        spinner = view.findViewById(R.id.spinnerCategorias)

        categoryViewModel.cargarCategorias()

        categoryViewModel.categorias.observe(viewLifecycleOwner) { lista ->
            val nombres = mutableListOf("Todo") + lista.map { it.name }

            val adapterSpinner = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                nombres
            )
            spinner.adapter = adapterSpinner

            // Seleccionar "Todo" al abrir
            spinner.setSelection(0)

            // Cargar todos los productos al iniciar
            viewModel.cargarProductos(reset = true)
        }

        // Al seleccionar una categoría, cargamos los productos de esa categoría en el RecyclerView
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val nombreSeleccionado = spinner.selectedItem as String
                val categoriaId = categoryViewModel.getCategoriaIdPorNombre(nombreSeleccionado)
                viewModel.cargarProductos(categoriaId = categoriaId, reset = true)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        viewModel.productos.observe(viewLifecycleOwner) { productos ->
            if (viewModel.paginaActual == 0) {
                adapter.updateData(productos) // Si es la primera página, reemplaza
            } else {
                adapter.appendData(productos) // Si es scroll, añade al final
            }
            viewModel.cargaInicial = false
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        // Carga inicial
        viewModel.cargarProductos()

        return view
    }
}
