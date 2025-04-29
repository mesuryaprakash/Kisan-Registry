package com.example.kisanregistry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kisanregistry.data.AppDatabase
import com.example.kisanregistry.data.model.User
import kotlinx.coroutines.launch

class SignUp : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var registerBtn: Button
    private lateinit var loginLink: TextView

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        name = findViewById(R.id.etName)
        email = findViewById(R.id.etEmail)
        username = findViewById(R.id.etSignupUsername)
        password = findViewById(R.id.etSignupPassword)
        registerBtn = findViewById(R.id.btnRegister)
        loginLink = findViewById(R.id.tvLoginLink)

        db = AppDatabase.getDatabase(this)

        registerBtn.setOnClickListener {
            val n = name.text.toString()
            val e = email.text.toString()
            val user = username.text.toString()
            val pass = password.text.toString()

            if (n.isEmpty() || e.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Implement your registration logic here (e.g., save to Room database)

                val newUser = User(
                    username = user,
                    password = pass,
                    name = n,
                    email = e
                )

                // Insert into database (Inside coroutine)
                lifecycleScope.launch {
                    try {
                        db.userDao().insertUser(newUser)
                        runOnUiThread {
                            Toast.makeText(this@SignUp, "Registered successfully!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SignUp, Login::class.java))
                            finish()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@SignUp, "Username already exists!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

//                Toast.makeText(this, "Registered successfully (mock)", Toast.LENGTH_SHORT).show()
//                // Optionally, navigate back to the login page after successful registration
//                startActivity(Intent(this, Login::class.java))
//                finish() // Close the signup activity
//            }
        }

        loginLink.setOnClickListener {
            // Navigate back to the login activity
            startActivity(Intent(this, Login::class.java))
            finish() // Close the signup activity
        }
    }
}