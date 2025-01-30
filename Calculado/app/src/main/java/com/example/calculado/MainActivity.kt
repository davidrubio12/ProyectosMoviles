package com.example.calculado

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var miTextViweResult : TextView;

    private lateinit var numberOne : EditText;

    private lateinit var numberTwo : EditText;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        miTextViweResult = findViewById(R.id.textViewResult);

        numberOne = findViewById(R.id.editTextNumber);

        numberTwo = findViewById(R.id.editTextNumber2);




    }

    fun sumar(v : View){

        val num1 = numberOne.text.toString().toDouble()
        val num2 = numberTwo.text.toString().toDouble()

        val resultado = num1 + num2

        miTextViweResult.text= "$resultado"

    }

    fun restar(v : View){

        val num1 = numberOne.text.toString().toDouble()
        val num2 = numberTwo.text.toString().toDouble()

        val resultado = num1 - num2

        miTextViweResult.text= "$resultado"

    }

    fun multiplicar(v : View){

        val num1 = numberOne.text.toString().toDouble()
        val num2 = numberTwo.text.toString().toDouble()

        val resultado = num1 * num2

        miTextViweResult.text= "$resultado"

    }

    fun dividir(v : View){

        val num1 = numberOne.text.toString().toDouble()
        val num2 = numberTwo.text.toString().toDouble()

        val resultado = num1 / num2

        miTextViweResult.text= "$resultado"

    }
}