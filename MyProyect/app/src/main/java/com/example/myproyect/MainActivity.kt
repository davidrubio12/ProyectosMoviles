package com.example.myproyect

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener,
    AdapterView.OnItemSelectedListener {





    private lateinit var miTextView : TextView;

    private lateinit var btn1 : ImageButton
    lateinit var text1: TextView
    lateinit var imagenView : ImageView

    lateinit var miImagenBoton : ImageButton

    lateinit var miImagenView : ImageView

    lateinit var select : Spinner

    lateinit var textOut : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        textOut = findViewById(R.id.textView5)
        select = findViewById(R.id.sp)
        val paises = arrayOf<String>("cuba","francia","italia")
        val miAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,paises)
        select.adapter = miAdapter

        miAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        miTextView = findViewById(R.id.tv1)

        btn1 = findViewById(R.id.imageButton)
        text1 = findViewById(R.id.text1)
        imagenView  = findViewById(R.id.iv1)

        miImagenBoton = findViewById(R.id.imb1)

        miImagenView = findViewById(R.id.imv2)

        miImagenBoton.setOnClickListener(View.OnClickListener {
            miImagenView.setImageResource(R.drawable.patricio2)
        })

        select.setOnItemSelectedListener(this)

//
//        btn1.setOnClickListener(View.OnClickListener {
//            text1.text = "HOLA CARACOLA"
//            text1.setTextColor(Color.RED)
//        })

        btn1.setOnClickListener(this)
        btn1.setOnLongClickListener(this)




    }

    fun hola(v: View){
        miTextView.text="Adios"
        miTextView.setTextColor(Color.RED)
        miTextView.setTextSize(34F)

    }

    override fun onClick(v: View?) {
        text1.text = "HOLA CARACOLA"
        text1.setTextColor(Color.RED)
        imagenView.setImageResource(R.drawable.sigue_trabajando)
        Log.i("Mi primera app","Mi primer mensaje")
    }

    override fun onLongClick(v: View?): Boolean {
        text1.text = "HOLA LENTTOOOOO"
        text1.setTextColor(Color.BLUE)
        return true
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
      textOut.text = 
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}