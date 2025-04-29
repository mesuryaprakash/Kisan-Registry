package com.example.kisanregistry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class TermsAndConditionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)

        // You can optionally get a reference to the TextView if you need to manipulate it further
        // val termsTextView: TextView = findViewById(R.id.termsAndConditionsTextView)
        // You could load the text dynamically here if needed.
    }
}