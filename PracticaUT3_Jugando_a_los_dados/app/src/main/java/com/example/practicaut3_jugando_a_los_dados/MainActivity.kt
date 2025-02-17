package com.example.practicaut3_jugando_a_los_dados

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var saldo = 100
    private lateinit var toggleGroup: MaterialButtonToggleGroup
    private lateinit var spinner: Spinner
    private lateinit var edtApuesta: EditText
    private lateinit var  btnLanzar : Button
    private lateinit var txtSaldo: TextView
    private lateinit var txtDado1:TextView
    private lateinit var txtDado2:TextView
    private lateinit var imgResultado :ImageView
    private lateinit var myCoordinator : View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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
            val apuestaStr = edtApuesta.text.toString()


            if (apuestaStr.isEmpty()) {
                Snackbar.make(myCoordinator, "Por favor, ingrese una apuesta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val apuesta = apuestaStr.toIntOrNull()
            if (apuesta == null || apuesta <= 0) {
                Snackbar.make(myCoordinator, "Ingrese un número válido mayor que 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar que la apuesta no sea mayor al saldo
            if (apuesta > saldo) {
                Snackbar.make(myCoordinator, "No puedes apostar más de tu saldo actual", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val opcionElegida = spinner.selectedItem?.toString()
            if (opcionElegida == null) {
                Snackbar.make(myCoordinator, "Seleccione una opción de apuesta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Si la apuesta es válida, continuar con la lógica del juego
            Toast.makeText(this, "Apuesta válida: $apuesta€", Toast.LENGTH_SHORT).show()
            Glide.with(this).load(R.drawable.dado_imagen_animada_0092).into(imgResultado)
            imgResultado.visibility = ImageView.VISIBLE

            // Simulación de lanzamiento con retardo de 3 segundos
            Handler(Looper.getMainLooper()).postDelayed({
                val dado1 = Random.nextInt(1, 7)
                val dado2 = Random.nextInt(1, 7)
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
                    Toast.makeText(this, "¡Ganaste $apuesta€!", Toast.LENGTH_SHORT).show()
                } else {
                    saldo -= apuesta
                    imgResultado.setImageResource(R.drawable.perder_dados)
                    Toast.makeText(this, "¡Perdiste $apuesta€!", Toast.LENGTH_SHORT).show()
                }

                txtSaldo.text = "Saldo: $saldo€"

                // Verificar si el saldo es 0 y terminar el juego
                if (saldo == 0) {
                    Toast.makeText(this, "Te has quedado sin saldo. Fin del juego.", Toast.LENGTH_LONG).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        preguntarSeguirJugando() // Cierra la app después de 1.5 segundos
                    }, 1500)
                } else {
                    // Preguntar si el jugador quiere seguir jugando después de 1 segundo
                    Handler(Looper.getMainLooper()).postDelayed({

                    }, 1000)
                }
            }, 3000)
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
        }




