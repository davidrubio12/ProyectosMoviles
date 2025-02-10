package com.example.mismensajes

import android.content.DialogInterface
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(){

    private lateinit var tv1 : TextView
    private lateinit var coor : CoordinatorLayout
    private lateinit var miVista : View
    private lateinit var imagen1 : ImageView
    private lateinit var perras : AnimationDrawable
    private lateinit var imagenTraga : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tv1 = findViewById(R.id.tv1)

        coor = findViewById(R.id.coorLay1)

        imagen1 = findViewById(R.id.imV1)

        imagenTraga = findViewById(R.id.tragaperras)

        imagenTraga.setBackgroundResource(R.drawable.tragaperras)



        perras = imagenTraga.background as AnimationDrawable

//        Glide.with(this).load(R.drawable.dado_imagen_animada_0092).into(imagen1);
    }

    fun mitoast(v : View){
        perras.start()
    }

    fun snackBar(v : View){
//        Snackbar.make(coor, "Adios caracola ", Snackbar.LENGTH_LONG).setAction("Accion", {
//            tv1.text = "Hola de nuevo"
//        })
//            .setActionTextColor(Color.RED).show()
       perras.stop()
    }

    fun alert(v : View){
        var myAlert = AlertDialog.Builder(this)
        myAlert.setTitle("Alerta")
        myAlert.setMessage("Esta es una alerta")
        myAlert.setPositiveButton("OK",({ dialog, which ->
            tv1.text="Has puesto ok"
        }))
        myAlert.setNegativeButton("Cancelar",DialogInterface.OnClickListener({dialog, which ->
          this.finish()
        }))
        myAlert.setNeutralButton("Esto es un boton mucho mas largo",null)
        myAlert.create().show()

    }

    fun customAlert( v : View){
        var myAlert = AlertDialog.Builder(this)
        miVista = layoutInflater.inflate(R.layout.login,null)
        myAlert.setView(miVista)
        myAlert.setPositiveButton("OK",({ dialog, which ->
            tv1.text=miVista.findViewById<EditText>(R.id.username).text
        }))
        myAlert.setNegativeButton("Cancelar",DialogInterface.OnClickListener({dialog, which ->
            this.finish()
        }))
        myAlert.setNeutralButton("Esto es un boton mucho mas largo",null)
        myAlert.create().show()
    }



}