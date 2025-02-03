package com.example.ut2_quizmatemticopractica2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView

import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var btnRandom : Button

    private lateinit var tvRandom : TextView


    private lateinit var btnResult : Button

    private lateinit var tvResult : TextView

    private lateinit var cbDiv2 : CheckBox

    private lateinit var cbDiv3 : CheckBox

    private lateinit var cbDiv5 : CheckBox

    private lateinit var cbDiv10 : CheckBox

    private lateinit var cbNone : CheckBox

    private lateinit var iOk : ImageView

    private lateinit var iNo : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iNo = findViewById(R.id.iNo);
        iOk = findViewById(R.id.iOk);
        cbDiv2 = findViewById(R.id.cB2);
        cbDiv3 = findViewById(R.id.cB3);
        cbDiv5 = findViewById(R.id.cB5);
        cbDiv10 = findViewById(R.id.cB10);
        cbNone = findViewById(R.id.cBNot);

        btnRandom = findViewById(R.id.btnRandom);

        tvRandom = findViewById(R.id.tvRandom);

        btnResult = findViewById(R.id.bResult);

        tvResult = findViewById(R.id.tvResult);

        var numRandom = ""

        btnRandom.setOnClickListener(View.OnClickListener {

            numRandom = randomNumber().toString()

            tvRandom.text = numRandom ;

        })


        btnResult.setOnClickListener(View.OnClickListener {
            if (numRandom == ""){
                tvResult.text = "Tienes que generar primero el numero aleatorio"
            }else{
                esDivisible(numRandom.toInt());
            }

        })

    }
    private fun randomNumber(): Int {
        val random = Random.nextInt(1000,2000);

        return random;
    }

    private fun esDivisible(numRandom : Int){
        val isDiv2 = numRandom % 2 == 0
        val isDiv3 = numRandom % 3 == 0
        val isDiv5 = numRandom % 5 == 0
        val isDiv10 = numRandom % 10 == 0
        val isNone = !isDiv2 && !isDiv3 && !isDiv5 && !isDiv10

        val anyChecked = cbDiv2.isChecked || cbDiv3.isChecked || cbDiv5.isChecked || cbDiv10.isChecked || cbNone.isChecked

        if (!anyChecked) {
            tvResult.text = "Debe escoger al menos una de las opciones"
            tvResult.setTextColor(Color.BLUE)
            return
        }

        var correct = true
        if (cbDiv2.isChecked && !isDiv2) correct = false
        if (cbDiv3.isChecked && !isDiv3) correct = false
        if (cbDiv5.isChecked && !isDiv5) correct = false
        if (cbDiv10.isChecked && !isDiv10) correct = false
        if (cbNone.isChecked && !isNone) correct = false

        if (correct) {
            tvResult.text = "¡Correcto!"
            tvResult.setTextColor(Color.GREEN)
            iOk.visibility =View.VISIBLE
            iNo.visibility = View.INVISIBLE


        } else {
            tvResult.text = "Incorrecto. Inténtalo de nuevo."
            tvResult.setTextColor(Color.RED)
            iOk.visibility =View.INVISIBLE
            iNo.visibility = View.VISIBLE
        }
    }
}