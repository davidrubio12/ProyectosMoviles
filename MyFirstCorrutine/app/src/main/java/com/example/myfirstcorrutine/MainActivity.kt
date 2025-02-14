package com.example.myfirstcorrutine

import android.os.Bundle
import android.os.Message
import android.os.Process
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myfirstcorrutine.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private lateinit var corrutine : Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding){
            btToast.setOnClickListener{showToast("Hola")}

            btSinHilos.setOnClickListener{

            longTask(pB1,myTV)

            }


            btConHilos.setOnClickListener{
                corrutine = lifecycleScope.launch {
                    longTaskKotlin(pB1,myTV)
                }
            }

            btStop.setOnClickListener{
                corrutine.cancel()
                myTV.text = "Tarea cancelada"
            }


        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    private fun longTask(pB1: ProgressBar,myTV : TextView){
        pB1.max = 100
        pB1.setProgress(0)
        for (i in 1 .. 10){
            Thread.sleep(1000)
            pB1.progress = i * 10
        }
        showToast("Tarea finalizada")
        myTV.text = "Tarea finalizada"



    }

    private suspend fun  longTaskKotlin(pB1: ProgressBar,myTV : TextView){


        pB1.max = 100
        pB1.setProgress(0)
        for (i in 1 .. 10){
            delay(1000)
            pB1.progress = i * 10
        }
        showToast("Tarea finalizada")
        myTV.text = "Tarea finalizada"
    }


}