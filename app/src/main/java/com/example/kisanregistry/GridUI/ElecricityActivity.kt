package com.example.kisanregistry.GridUI

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kisanregistry.HomeDashboard
import com.example.kisanregistry.R

class ElecricityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_elecricity)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val backbtn: ImageView = findViewById(R.id.icon_menu)
        backbtn.setOnClickListener {
            val backIntent = Intent(this, HomeDashboard::class.java)
            startActivity(backIntent)
        }
    }
}