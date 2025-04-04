package com.example.menunavegacion

import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.menunavegacion.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

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
                    if(it.itemId == R.id.id_inicio){

                        val myFragmentManager: FragmentManager = supportFragmentManager
                        val myTransactionFragment: FragmentTransaction = myFragmentManager.beginTransaction()
                        val myFragment: webFragment = webFragment.newInstance("https://iesclaradelrey.es/portal/index.php/es/")
                        myTransactionFragment.add(R.id.myLinerLayout, myFragment).commit()
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