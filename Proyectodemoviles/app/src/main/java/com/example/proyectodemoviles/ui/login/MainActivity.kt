package com.example.proyectodemoviles.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.databinding.ActivityMainBinding
import com.example.proyectodemoviles.ui.cart.CartFragment
import com.example.proyectodemoviles.ui.product.ProductFragment
import com.example.proyectodemoviles.ui.web.webFragment
import com.example.proyectodemoviles.util.SessionManager
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    lateinit var materialToolbar: MaterialToolbar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (SessionManager.accessToken!!.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

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


            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                main,
                materialToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            main.addDrawerListener(toggle)
            toggle.syncState()

            myNavigationView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.id_inicio -> {
                        val myFragmentManager: FragmentManager = supportFragmentManager
                        val myTransactionFragment: FragmentTransaction = myFragmentManager.beginTransaction()
                        val myFragment: webFragment = webFragment.Companion.newInstance("http://10.0.2.2:8000/home/app")
                        myTransactionFragment.replace(R.id.myLinerLayout, myFragment).commit()
                    }
                    R.id.id_productos -> {
                        val fragmentManager = supportFragmentManager
                        val transaction = fragmentManager.beginTransaction()
                        val productosFragment = ProductFragment()
                        transaction.replace(R.id.myLinerLayout, productosFragment).commit()
                    }
                    R.id.id_carrito -> {

                        val carritoFragment = CartFragment()
                        val transaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.myLinerLayout, carritoFragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    R.id.id_logout -> {
                       logout()
                        true
                    }
                }
                true
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        SessionManager.accessToken = "" // Limpia el token
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}