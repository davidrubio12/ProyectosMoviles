package com.example.practicaut3_jugando_a_los_dados

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var saldo = 100
    private var juegoJob: Job? = null  // Variable para controlar la corrutina del juego

    private lateinit var toggleGroup: com.google.android.material.button.MaterialButtonToggleGroup
    private lateinit var spinner: Spinner
    private lateinit var edtApuesta: EditText
    private lateinit var btnLanzar: Button
    private lateinit var txtSaldo: TextView
    private lateinit var txtDado1: TextView
    private lateinit var txtDado2: TextView
    private lateinit var imgResultado: ImageView
    private lateinit var myCoordinator: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtDado1 = findViewById(R.id.txtDado1)
        txtDado2 = findViewById(R.id.txtDado2)
        imgResultado = findViewById(R.id.imgResultado)
        toggleGroup = findViewById(R.id.toggleGroup)
        spinner = findViewById(R.id.spinner1)
        edtApuesta = findViewById(R.id.edtApuesta)
        btnLanzar = findViewById(R.id.btnLanzar)
        txtSaldo = findViewById(R.id.txtSaldo)
        myCoordinator = findViewById(R.id.frameLayout)

        txtSaldo.text = "Saldo: $saldo€"

        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                val opciones = when (checkedId) {
                    R.id.btnParImpar -> listOf("Par", "Impar")
                    R.id.btnMayorMenor -> listOf("Mayor o igual a 7", "Menor a 7")
                    else -> emptyList()
                }

                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
                spinner.adapter = adapter
            }
        }

        btnLanzar.setOnClickListener {
            iniciarJuego()
        }
    }

    private fun iniciarJuego() {
        val apuestaStr = edtApuesta.text.toString()

        if (apuestaStr.isEmpty()) {
            Snackbar.make(myCoordinator, "Por favor, ingrese una apuesta", Snackbar.LENGTH_SHORT).show()
            return
        }

        val apuesta = apuestaStr.toIntOrNull()
        if (apuesta == null || apuesta <= 0) {
            Snackbar.make(myCoordinator, "Ingrese un número válido mayor que 0", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (apuesta > saldo) {
            Snackbar.make(myCoordinator, "No puedes apostar más de tu saldo actual", Snackbar.LENGTH_SHORT).show()
            return
        }

        val opcionElegida = spinner.selectedItem?.toString()
        if (opcionElegida == null) {
            Snackbar.make(myCoordinator, "Seleccione una opción de apuesta", Snackbar.LENGTH_SHORT).show()
            return
        }

        // Mostrar animación del dado
        Toast.makeText(this, "Apuesta válida: $apuesta€", Toast.LENGTH_SHORT).show()
        Glide.with(this).load(R.drawable.dado_imagen_animada_0092).into(imgResultado)
        imgResultado.visibility = ImageView.VISIBLE

        // Si hay una corrutina activa, la cancelamos antes de iniciar otra
        juegoJob?.cancel()

        // Lanza la lógica del juego con una nueva corrutina
        juegoJob = CoroutineScope(Dispatchers.Main).launch {
            lanzarDados(apuesta, opcionElegida)
        }
    }

    private suspend fun lanzarDados(apuesta: Int, opcionElegida: String) {
        delay(3000) // Simula el tiempo de lanzamiento de los dados

        val dado1 = (1..6).random()
        val dado2 = (1..6).random()
        val sumaDados = dado1 + dado2

        txtDado1.text = "$dado1"
        txtDado2.text = "$dado2"

        val gano = when {
            opcionElegida == "Par" && sumaDados % 2 == 0 -> true
            opcionElegida == "Impar" && sumaDados % 2 != 0 -> true
            opcionElegida == "Mayor o igual a 7" && sumaDados >= 7 -> true
            opcionElegida == "Menor a 7" && sumaDados < 7 -> true
            else -> false
        }

        if (gano) {
            saldo += apuesta
            imgResultado.setImageResource(R.drawable.ganar_dados)
            Toast.makeText(this@MainActivity, "¡Ganaste $apuesta€!", Toast.LENGTH_SHORT).show()
        } else {
            saldo -= apuesta
            imgResultado.setImageResource(R.drawable.perder_dados)
            Toast.makeText(this@MainActivity, "¡Perdiste $apuesta€!", Toast.LENGTH_SHORT).show()
        }

        txtSaldo.text = "Saldo: $saldo€"

        // Verificar si el saldo es 0 y terminar el juego
        if (saldo == 0) {
            Toast.makeText(this@MainActivity, "Te has quedado sin saldo. Fin del juego.", Toast.LENGTH_LONG).show()
            imgResultado.setImageResource(R.drawable.bancarrota)
            delay(1500) // Espera 1.5 segundos antes de cerrar
            finish()
        } else {
            delay(1000) // Espera 1 segundo antes de preguntar si sigue jugando
            preguntarSeguirJugando()
        }
    }

    private fun preguntarSeguirJugando() {
        val alertDialog = android.app.AlertDialog.Builder(this)
            .setTitle("¿Quieres seguir jugando?")
            .setPositiveButton("Sí") { _, _ -> }
            .setNegativeButton("No") { _, _ -> finish() }
            .create()
        alertDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        juegoJob?.cancel() // Cancelamos la corrutina para evitar fugas de memoria
    }
}
