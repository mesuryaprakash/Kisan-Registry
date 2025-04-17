package com.example.kisanregistry

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kisanregistry.data.FarmerDatabase
import com.example.kisanregistry.data.model.farmer
import com.example.kisanregistry.ui.adapter.FarmerAdapter
import com.example.kisanregistry.ui.viewmodel.FarmerViewModel
import com.example.kisanregistry.ui.viewmodel.FarmerViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var farmerViewModel: FarmerViewModel
    private lateinit var adapter: FarmerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize your database and DAO
        val farmerDao = FarmerDatabase.getDatabase(this).farmerDao()

        // Initialize ViewModel using Factory
        val viewModelFactory = FarmerViewModelFactory(farmerDao)
        val farmerViewModel = ViewModelProvider(this, viewModelFactory)[FarmerViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.farmerRecyclerView)
        adapter = FarmerAdapter(
            emptyList(),
            onDelete = { farmer -> farmerViewModel.deleteFarmer(farmer) },
            onEdit = { farmer ->
                // TODO: open dialog to update farmer
                println("Editing Farmer: ${farmer.name}")
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Example: Observe Farmers
        farmerViewModel.allFarmers.observe(this) { farmers -> adapter.updateData(farmers)
            // TODO: Update UI (RecyclerView, TextView, etc.)
//            println("Current Farmers in DB: $farmers")
        }

        // Example: Insert a new Farmer (Testing)
        farmerViewModel.insertFarmer(
            farmer(
                id = 0,
                name = "Ramesh Kumar",
                village = "Rampur",
                landSize = 90.5
            )
        )
    }
}