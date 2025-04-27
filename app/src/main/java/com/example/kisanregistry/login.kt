package com.example.kisanregistry
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class login : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var signupLink: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("KisanRegistryPrefs", Context.MODE_PRIVATE)

        // Check if user is already logged in
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        val savedUsername = sharedPreferences.getString("username", null)

        if (isLoggedIn && savedUsername != null) {
            // If user is logged in, navigate to the HomeDashboard activity
            val dashboardIntent = Intent(this, HomeDashboard::class.java)
            dashboardIntent.putExtra("USERNAME", savedUsername)
            startActivity(dashboardIntent)
            finish() // Close the login activity
        }

        username = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        loginBtn = findViewById(R.id.btnLogin)
        signupLink = findViewById(R.id.tvSignupLink)

        loginBtn.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Validate user from Room database later
                // For now, we simulate a successful login
                Toast.makeText(this, "Logged in (mock)", Toast.LENGTH_SHORT).show()

                // Save login state in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("username", user)
                editor.apply()

                // Navigate to the HomeDashboard activity
                val dashboardIntent = Intent(this, HomeDashboard::class.java)
                dashboardIntent.putExtra("USERNAME", user)
                startActivity(dashboardIntent)
                finish() // Close the login activity
            }
        }

        signupLink.setOnClickListener {
            startActivity(Intent(this, signUp::class.java))
        }
    }
}

