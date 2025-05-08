package com.example.proyectodemoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.proyectodemoviles.model.MainState
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.btnLogin)
        val usernameEditText: EditText = findViewById(R.id.editTextUsername)
        val passwordEditText: EditText = findViewById(R.id.editTextPassword)

        loginButton.setOnClickListener {
            // Obtener el nombre de usuario y la contraseña ingresados
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Llamar al método de login
                login(username, password)
            } else {
                Toast.makeText(this, "Por favor ingrese un usuario y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(username: String, password: String) {
        // Llamada asíncrona a login en el backend usando Retrofit y coroutines
        lifecycleScope.launch {
            // Instanciamos el MainState, donde se hace la llamada a Retrofit
            val mainState = MainState()

            try {
                val jwtTokensDto = mainState.login(username, password)

                if (jwtTokensDto != null) {
                    // Guardar los tokens en SharedPreferences
                    val sharedPreferences = getSharedPreferences("appPrefs", MODE_PRIVATE)
                    sharedPreferences.edit().apply {
                        putString("access_token", jwtTokensDto.accessToken)
                        putString("refresh_token", jwtTokensDto.refreshToken)
                        apply()
                    }

                    // Si el login es exitoso, redirigir a MainActivity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()  // Cerrar la pantalla de login
                } else {
                    Toast.makeText(this@LoginActivity, "Login fallido. Verifica tus credenciales.", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                // En caso de que haya un error con Retrofit o la red
                Toast.makeText(this@LoginActivity, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
