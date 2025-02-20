package com.example.ciclo_de_vida

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ciclo_de_vida.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var numClicks = 0 ;
    private lateinit var edit1 : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.tv1.text = "0";
        binding.btnSum.setOnClickListener({
            binding.tv1.text =
                (binding.tv1.text.toString().toInt() + binding.editCalculo.text.toString().toInt()).toString()
            numClicks++
            if (numClicks == 5){
                Toast.makeText(this,"Hemos llegado a 5 clicks", Toast.LENGTH_SHORT).show()
                numClicks = 0;
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("contador",binding.tv1.text.toString())
        outState.putInt("numClicks",numClicks)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)

        numClicks = savedInstanceState.getInt("numClicks")

        binding.tv1.text = savedInstanceState.getString("contador")

    }
}