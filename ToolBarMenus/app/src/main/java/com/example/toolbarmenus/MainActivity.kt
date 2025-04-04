package com.example.toolbarmenus

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.motion.MaterialMainContainerBackHelper

class MainActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var textView: TextView
    lateinit var materialToolbarMorfeo: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        materialToolbarMorfeo = findViewById(R.id.materialToolbar)
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.tV2)
        setSupportActionBar(materialToolbarMorfeo)

        supportActionBar?.hide()
        imageView.setOnClickListener {
            supportActionBar!!.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.first_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.redpill) {
    textView.text = "Redpill"
        } else if (item.itemId == R.id.bluepill) {
            textView.text = "Bluepill"
        } else {
            this.finish()
        }
    return super.onOptionsItemSelected(item)
    }
}





