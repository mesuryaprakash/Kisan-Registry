package com.example.kisanregistry

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class SoilQualityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soil_quality)

        // Find the action LinearLayouts
        val locateLabOption = findViewById<CardView>(R.id.locateLabCard)
        val printCardOption = findViewById<CardView>(R.id.printCard)
        val testingHistoryOption = findViewById<CardView>(R.id.testingHistory)
        val fertilizersPesticidesOption = findViewById<CardView>(R.id.fertilizersPesticides)
        val helpDeskOption = findViewById<CardView>(R.id.helpDesk)

        // Set OnClickListeners for each action
        locateLabOption.setOnClickListener {
            // Implement logic to locate soil testing laboratory (e.g., open map)
            // For now, let's just show a message or start a placeholder activity
            // Example:
            // val intent = Intent(this, LocateLabActivity::class.java)
            // startActivity(intent)
            println("Locate soil testing laboratory clicked")
        }

        printCardOption.setOnClickListener {
            // Implement logic to print soil health card
            println("Print soil health card clicked")
        }

        testingHistoryOption.setOnClickListener {
            // Implement logic to view soil testing history
            println("Soil testing history clicked")
        }

        fertilizersPesticidesOption.setOnClickListener {
            // Implement logic to view fertilizers and pesticides information
            println("Fertilizers and Pesticides clicked")
        }

        helpDeskOption.setOnClickListener {
            // Implement logic to open help desk or support
            println("Help Desk clicked")
        }
    }
}