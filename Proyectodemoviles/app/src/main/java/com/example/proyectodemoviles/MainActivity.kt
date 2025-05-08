package com.example.proyectodemoviles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.example.proyectodemoviles.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    lateinit var materialToolbar: MaterialToolbar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        materialToolbar = findViewById(R.id.materialToolbar)

        setSupportActionBar(materialToolbar)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding){


            val toggle = ActionBarDrawerToggle(this@MainActivity,
                main,
                materialToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
            main.addDrawerListener(toggle)
            toggle.syncState()
//            myNavigationView.setNavigationItemSelectedListener ( this@MainActivity )

            myNavigationView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.id_inicio -> {
                        val myFragmentManager: FragmentManager = supportFragmentManager
                        val myTransactionFragment: FragmentTransaction = myFragmentManager.beginTransaction()
                        val myFragment: webFragment = webFragment.newInstance("https://iesclaradelrey.es/portal/index.php/es/")
                        myTransactionFragment.replace(R.id.myLinerLayout, myFragment).commit()
                    }
                    R.id.id_productos -> {
                        val fragmentManager = supportFragmentManager
                        val transaction = fragmentManager.beginTransaction()
                        val productosFragment = ProductFragment()
                        transaction.replace(R.id.myLinerLayout, productosFragment).commit()
                    }
                    R.id.id_carrito -> {
                        // Mostrar el fragmento de carrito
                        val carritoFragment = CartFragment()
                        val transaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.myLinerLayout, carritoFragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                }
                true
            }

        }

    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        TODO("Not yet implemented")
//
//    }
}