package com.example.kisanregistry

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class signUp : AppCompatActivity() {

    private lateinit var newUsername: EditText
    private lateinit var newPassword: EditText
    private lateinit var registerBtn: Button
    private lateinit var termsCheck: CheckBox

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
        newPassword = findViewById(R.id.etNewPassword)
        registerBtn = findViewById(R.id.btnRegister)
        termsCheck = findViewById(R.id.checkboxTerms)

        registerBtn.setOnClickListener {
            val user = newUsername.text.toString()
            val pass = newPassword.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else if (!termsCheck.isChecked) {
                Toast.makeText(this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Save user to Room DB here
                Toast.makeText(this, "Registered successfully (mock)", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}