package com.example.proyectodemoviles.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.util.SessionManager
import com.example.proyectodemoviles.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.btnLogin)
        val usernameEditText: EditText = findViewById(R.id.editTextUsername)
        val passwordEditText: EditText = findViewById(R.id.editTextPassword)

        loginViewModel.tokens.observe(this) { tokens ->
            if (tokens != null) {
                SessionManager.accessToken = tokens.accessToken
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        loginButton.setOnClickListener {
            // Obtener el nombre de usuario y la contraseña ingresados
            val email = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Llamar al método de login
                loginViewModel.login(email, password)
            } else {
                Toast.makeText(this, "Por favor ingrese un usuario y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

}