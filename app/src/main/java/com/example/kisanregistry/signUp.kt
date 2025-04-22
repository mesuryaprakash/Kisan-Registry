package com.example.kisanregistry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class signUp : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var registerBtn: Button
    private lateinit var loginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        name = findViewById(R.id.etName)
        email = findViewById(R.id.etEmail)
        username = findViewById(R.id.etSignupUsername)
        password = findViewById(R.id.etSignupPassword)
        registerBtn = findViewById(R.id.btnRegister)
        loginLink = findViewById(R.id.tvLoginLink)

        registerBtn.setOnClickListener {
            val n = name.text.toString()
            val e = email.text.toString()
            val user = username.text.toString()
            val pass = password.text.toString()

            if (n.isEmpty() || e.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Implement your registration logic here (e.g., save to Room database)
                Toast.makeText(this, "Registered successfully (mock)", Toast.LENGTH_SHORT).show()
                // Optionally, navigate back to the login page after successful registration
                startActivity(Intent(this, login::class.java))
                finish() // Close the signup activity
            }
        }

        loginLink.setOnClickListener {
            // Navigate back to the login activity
            startActivity(Intent(this, login::class.java))
            finish() // Close the signup activity
        }
    }
}