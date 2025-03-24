package com.example.ut42_api

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ut42_api.databinding.ActivityMainBinding
import com.example.ut42_api.model.MainViewModel
import com.example.ut42_api.recycler.MyAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myViewModel: MainViewModel by viewModels()
    lateinit var myAdapter: MyAdapter
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
        val mLayout = LinearLayoutManager(this@MainActivity)
            rvPerros.layoutManager = mLayout

            bt2.setOnClickListener{
                val raza = edt2.getText().toString()
                if (raza.isEmpty()){
                    Toast.makeText(this@MainActivity, "Ingrese una raza", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                }
                myViewModel.devuelveFotos(raza)
            }

            myViewModel.datos.observe(this@MainActivity){
                if(it.status =="success"){
                    myAdapter = MyAdapter(it)
                    rvPerros.adapter = myAdapter
                }else{
                    Toast.makeText(this@MainActivity, "Error al cargar los perros", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}