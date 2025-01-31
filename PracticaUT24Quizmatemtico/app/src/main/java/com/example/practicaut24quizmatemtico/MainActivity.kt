package com.example.practicaut24quizmatemtico

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var btnRandom : Button

    private lateinit var tvRandom : TextView

    private lateinit var tvBisiesto : TextView

   private lateinit var radioGroup : RadioGroup

    private lateinit var btnResult : Button

    private lateinit var tvResult : TextView

    private lateinit var main : View

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var sW1 : Switch


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnRandom = findViewById(R.id.btnRandom);

        tvRandom = findViewById(R.id.tvRandom);

        tvBisiesto = findViewById(R.id.tvBisiesto);

        radioGroup = findViewById(R.id.rG);

        btnResult = findViewById(R.id.bResult);

        tvResult = findViewById(R.id.tvResult);

        sW1 = findViewById(R.id.sW1)

        main = findViewById(R.id.main)

        var numRandom = ""

        btnRandom.setOnClickListener(View.OnClickListener {
            numRandom = randomNumber().toString()
            tvRandom.text = numRandom ;
        })

        btnResult.setOnClickListener(View.OnClickListener {
            if (numRandom == ""){
                tvResult.text = "Tienes que generar primero el numero aleatorio"
            }else{
                esBisiesto(numRandom.toInt());
            }

        })

        sW1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                main.setBackgroundColor(Color.YELLOW)
            }else
                main.setBackgroundColor(Color.WHITE)
        }


    }
    private fun randomNumber(): Int {
        val random = Random.nextInt(1900,2500);

        return random;
    }

    @SuppressLint("SetTextI18n")
    private fun esBisiesto(numR : Int){
        if (radioGroup.checkedRadioButtonId != -1){

            when(radioGroup.checkedRadioButtonId){

                R.id.rbSi ->{
                    if (calcularBisiesto(numR)){
                        tvResult.text = "Correcto!"
                        tvResult.setTextColor(Color.GREEN)
                    }else{
                        tvResult.text = "INCORRECTO!"
                        tvResult.setTextColor(Color.RED)
                    }
                }
                R.id.rbNo ->{
                    if (calcularBisiesto(numR)){
                        tvResult.text = "INCORRECTO!"
                        tvResult.setTextColor(Color.RED)
                    }else{
                        tvResult.text = "CORRECTO!"
                        tvResult.setTextColor(Color.GREEN)
                    }
                }
            }


        }else{
            tvResult.text = "Primero elige una opción"
            tvResult.setTextColor(Color.BLUE)
        }
    }

    private fun calcularBisiesto(anio: Int): Boolean {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)
    }


}