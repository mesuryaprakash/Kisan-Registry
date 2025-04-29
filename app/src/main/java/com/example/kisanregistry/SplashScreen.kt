package com.example.kisanregistry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SplashScreen: AppCompatActivity() {

    private lateinit var signInButton: Button
    private lateinit var createAccountButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        signInButton = findViewById(R.id.btnSignInSplash)
        createAccountButton = findViewById(R.id.btnCreateAccountSplash)

        signInButton.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        createAccountButton.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
    }
}