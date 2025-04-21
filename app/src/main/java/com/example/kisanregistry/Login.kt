package com.example.kisanregistry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var signupLink: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }



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
                Toast.makeText(this, "Logged in (mock)", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        signupLink.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
    }
}
