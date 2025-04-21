package com.example.kisanregistry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.kisanregistry.data.AppDatabase
import com.example.kisanregistry.data.model.User
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

import java.sql.Types.NULL


class SignUp : AppCompatActivity() {

    private lateinit var newUsername: EditText
    private lateinit var newPassword: EditText
    private lateinit var newName: EditText
    private lateinit var newEmail: EditText
    private lateinit var registerBtn: Button
    private lateinit var termsCheck: CheckBox
    private lateinit var loginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        newUsername = findViewById(R.id.etNewUsername)
        newName = findViewById(R.id.etNewName)
        newEmail = findViewById(R.id.etNewEmail)
        newPassword = findViewById(R.id.etNewPassword)
        registerBtn = findViewById(R.id.btnRegister)
        termsCheck = findViewById(R.id.checkboxTerms)
        loginLink = findViewById(R.id.tvLoginLink)

        registerBtn.setOnClickListener {
            val user = newUsername.text.toString().trim()
            val pass = newPassword.text.toString().trim()
            val name = newName.text.toString().trim()
            val email = newEmail.text.toString().trim()

            if (user.isEmpty() || pass.isEmpty() || name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else if (!termsCheck.isChecked) {
                Toast.makeText(this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show()
            } else {
                val newUser = User(
                    username = user,
                    password = pass,
                    name = name,
                    email = email,
                )

                val db = AppDatabase.getDatabase(this)
                val userDao = db.userDao()

                lifecycleScope.launch {
                    val existingUser = userDao.getUserByUsername(user)  // You'll need a non-LiveData query here.
                    if (existingUser != null) {
                        runOnUiThread {
                            Toast.makeText(this@SignUp, "Username already exists!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        userDao.insertUser(newUser)
                        runOnUiThread {
                            Toast.makeText(this@SignUp, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SignUp, Login::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }
}