package com.example.ut2_spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    private lateinit var sp1 : Spinner
    private lateinit var sp2 : Spinner
    private lateinit var miTexto : TextView
    private lateinit var imagenView: ImageView

    private val temasRelacionados = mapOf(
        "Serie" to arrayOf("Stranger Things", "Miercoles", "Game of Thrones"),
        "Juegos" to arrayOf("GTA 6", "The Legend Of Zelda", "Metal Gear Solid"),
        "Pelicula" to arrayOf("Titanic", "Forrest Gump", "Harry Potter")
    )

    private val imagenesRelacionadas = mapOf(
        "Stranger Things" to R.drawable.strangerthings,
        "Miercoles" to R.drawable.miercoles,
        "Game of Thrones" to R.drawable.gameofthrone,
        "GTA 6" to R.drawable.gta6,
        "The Legend Of Zelda" to R.drawable.zelda,
        "Metal Gear Solid" to R.drawable.metalgear,
        "Titanic" to R.drawable.titanic,
        "Forrest Gump" to R.drawable.forrestgump,
        "Harry Potter" to R.drawable.harrypotter)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        miTexto = findViewById(R.id.tv1);
        imagenView = findViewById(R.id.imgV1);

        val temas = temasRelacionados.keys.toTypedArray()
        val miAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, temas)
        miAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp1.adapter = miAdapter
        sp1.onItemSelectedListener = this

        sp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val itemSeleccionado = sp2.selectedItem?.toString()
                val imagenId = imagenesRelacionadas[itemSeleccionado]
                if (imagenId != null) {
                    imagenView.setImageResource(imagenId)
                } else {
                    imagenView.setImageResource(0)
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                imagenView.setImageResource(0)
            }
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { val temaSeleccionado = sp1.selectedItem.toString()
        miTexto.text = temaSeleccionado

        val opciones = temasRelacionados[temaSeleccionado] ?: emptyArray()
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sp2.adapter = adapter2
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}