package com.example.ut4_recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ut4_recyclerview.databinding.ActivityMainBinding
import com.example.ut4_recyclerview.model.MainViewModel
import com.example.ut4_recyclerview.recycler.MyAdapter

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


        with(binding) {
            myViewModel.devuelveArray();

            val mLayout = LinearLayoutManager(this@MainActivity)

            rvColores.layoutManager = mLayout


            myViewModel.datos.observe(this@MainActivity) {
                myAdapter = MyAdapter(it)
                rvColores.adapter = myAdapter

                val myDivider = DividerItemDecoration(rvColores.context,mLayout.orientation)

                rvColores.addItemDecoration(myDivider)

            }
        }


    }
}