package com.example.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.MainViewModel
import com.example.recyclerview.recycler.MyAdapter
import kotlinx.coroutines.launch

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

            rvAnimales.layoutManager = mLayout


            myViewModel.datos.observe(this@MainActivity) {
                myAdapter = MyAdapter(it)
                rvAnimales.adapter = myAdapter

                val myDivider = DividerItemDecoration(rvAnimales.getContext(),mLayout.orientation)

                rvAnimales.addItemDecoration(myDivider)

            }

            btnDelete.setOnClickListener{
                if (myAdapter.clickPosition < 0){
                    Toast.makeText(applicationContext,"Debe seleccionar una fila", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                myViewModel.delete(myAdapter.clickPosition)
            }

            myViewModel._delete.observe(this@MainActivity){
                myAdapter.notifyItemRemoved(it.position)
                myAdapter.clickPosition = RecyclerView.NO_POSITION
               myAdapter.notifyItemRangeChanged(0,it.animales.size)
            }
            bntadd.setOnClickListener {
                var position = myAdapter.clickPosition
                if (position< 0){
                    position = 0
                }
                myViewModel.add(position,textInput.text.toString())
            }

            myViewModel.add.observe(this@MainActivity){
                myAdapter.notifyItemInserted(it.position)
                myAdapter.notifyItemRangeChanged(0,it.animales.size)

            }
        }


    }
}