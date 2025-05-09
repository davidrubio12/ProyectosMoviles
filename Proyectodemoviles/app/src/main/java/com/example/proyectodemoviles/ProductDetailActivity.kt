package com.example.proyectodemoviles

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.proyectodemoviles.util.Cart
import com.example.proyectodemoviles.util.CartList

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        val imageView = findViewById<ImageView>(R.id.imageViewProducto)
        val editCantidad = findViewById<EditText>(R.id.editCantidad)
        val btnAgregar = findViewById<Button>(R.id.btnAgregarCarrito)

        // Recuperar datos pasados por intent
        val nombre = intent.getStringExtra("nombre_producto")
        val imagenUrl = intent.getStringExtra("url_imagen")
        val precio = intent.getDoubleExtra("precio_producto", 0.0)

        // Mostrar imagen
        Glide.with(this).load(imagenUrl).into(imageView)

        btnAgregar.setOnClickListener {
            val cantidad = editCantidad.text.toString().toIntOrNull()

            if (cantidad != null && cantidad > 0) {
                val producto = Cart(
                    nombre = nombre ?: "Producto",
                    cantidad = cantidad,
                    precio = precio
                )
                CartList.agregarProducto(producto)
                Toast.makeText(this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Introduce una cantidad válida", Toast.LENGTH_SHORT).show()
            }
        }
    }
}