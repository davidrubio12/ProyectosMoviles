package com.example.myintenst_explicitos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var myActivityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        findViewById<Button>(R.id.btnPrincipal).setOnClickListener{
            var myIntent : Intent = Intent(this, Actividad2::class.java)
            myIntent.putExtra("Texto",findViewById<EditText>(R.id.editTextPrincipal).text.toString())
            startActivity(myIntent)
        }
        myActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result: ActivityResult? ->
            if (result!!.resultCode == Activity.RESULT_OK){
                val miIntentResultado = result.data
                findViewById<TextView>(R.id.tvPrincipal).text = miIntentResultado!!.extras!!.getString("mensajeBack")

            }else{
                Toast.makeText(this,"ha fallado la vuelva", Toast.LENGTH_LONG).show()
            }
        }
        val myEdt = findViewById<EditText>(R.id.editTextPrincipal)
        val myBt = findViewById<Button>(R.id.btnPrincipal)
        myBt.setOnClickListener{
            val myIntent = Intent(this,Actividad2::class.java)
            myIntent.putExtra("mensaje", myEdt.text.toString())
            myActivityResultLauncher.launch(myIntent)
        }
    }
}