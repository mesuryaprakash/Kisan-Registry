package com.example.kisanregistry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreen : AppCompatActivity() {

    private lateinit var goToSignUpBtn: Button
    private lateinit var goToLoginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        goToSignUpBtn = findViewById(R.id.goToSignUp)
        goToLoginBtn = findViewById(R.id.goToLogin)

        goToSignUpBtn.setOnClickListener {
            startActivity(Intent(this, login::class.java))
        }
        goToLoginBtn.setOnClickListener {
            startActivity(Intent(this, signUp::class.java))
        }

    }
}