package com.example.proyectodemoviles.ui.product

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.model.Cart
import com.example.proyectodemoviles.model.CartList
import com.example.proyectodemoviles.viewmodel.CartViewModel

class ProductDetailActivity : AppCompatActivity() {

    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        val imageView = findViewById<ImageView>(R.id.imageViewProducto)
        val editCantidad = findViewById<EditText>(R.id.editCantidad)
        val btnAgregar = findViewById<Button>(R.id.btnAgregarCarrito)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        val textNombre = findViewById<TextView>(R.id.textNombre)
        val textPrecio = findViewById<TextView>(R.id.textPrecio)



        // Recuperar datos pasados por intent
        val nombre = intent.getStringExtra("nombre_producto")
        val imagenUrl = intent.getStringExtra("url_imagen")
        val precio = intent.getDoubleExtra("precio_producto", 0.0)
        val productId = intent.getLongExtra("producto_id", -1L)

        textNombre.text = nombre
        textPrecio.text = "€ %.2f".format(precio)

        // Mostrar imagen
        Glide.with(this).load(imagenUrl).into(imageView)

        cartViewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        btnAgregar.setOnClickListener {
            val cantidad = editCantidad.text.toString().toIntOrNull()

            if (cantidad != null && cantidad > 0 && productId > 0) {
                cartViewModel.añadirProducto(productId, cantidad)
                Toast.makeText(this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Introduce una cantidad válida", Toast.LENGTH_SHORT).show()
            }
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }
}