package com.example.kisanregistry

import SchemeAdapter
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kisanregistry.databinding.ActivityFarmerSchemesBinding
import com.example.kisanregistry.models.Scheme
class FarmerSchemes : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SchemeAdapter
    private lateinit var schemes: List<Scheme>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer_schemes)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        recyclerView = findViewById(R.id.schemeRecyclerView)

        // Initialize the schemes and adapter
        val dummySchemes = listOf(Scheme(
                "Pradhan Mantri Kisan Samman Nidhi (PM-KISAN)",
                "PM Kisan is a Central Sector scheme with 100% funding from the Government of India. The scheme provides income support of ₹6000 per year to small and marginal farmers to enable them to purchase inputs for their agricultural activities.",
                "Income Support",
                "Small and marginal farmers",
                "https://pmkisan.gov.in"
            ), Scheme(
                "Soil Health Card Scheme",
                "The Soil Health Card Scheme aims to provide farmers with a comprehensive report on the health of their soil, including information on nutrient deficiencies and the necessary corrective measures.",
                "Awareness",
                "All farmers",
                "https://soilhealth.dac.gov.in/"
            ), Scheme(
                "Pradhan Mantri Krishi Sinchayee Yojana (PMKSY)",
                "PMKSY focuses on ensuring that every farm gets adequate irrigation facilities. ",
                "Irrigation Infrastructure",
                "All farmers",
                "https://pmksy.gov.in"
            ), Scheme(
                "National Mission on Agricultural Extension and Technology",
                "The NMAET focuses on improving agricultural extension services to enhance farmers' productivity and income.",
                "Agricultural Extension",
                "Farmers, agricultural extension workers, and institutions",
                "https://agricoop.gov.in/en/Major#gsc.tab=0"
            ),Scheme(
                "Rashtriya Krishi Vikas Yojana (RKVY)",
                "The RKVY is a state-led scheme aimed at enhancing the agricultural sector by improving infrastructure and providing financial assistance for projects related to farming. It focuses on building irrigation facilities, providing high-quality seeds, improving soil health, and increasing farm mechanization. ",
                " Infrastructure and Development",
                "Farmers, agricultural extension workers, and institutions",
                "https://rkvy.nic.in"
            ),Scheme(
                "Fasal Bima Yojana (Crop Insurance Scheme)",
                "The Pradhan Mantri Fasal Bima Yojana (PMFBY) aims to provide financial support to farmers in case of crop loss due to natural calamities like floods, droughts, or pest attacks.",
                "Risk Mitigation",
                "Farmers",
                "https://pmfby.gov.in"
            ),Scheme(
                "Kisan Credit Card (KCC) Scheme",
                "The Kisan Credit Card (KCC) scheme is designed to provide short-term credit to farmers for agricultural operations. Farmers can avail themselves of loans to purchase inputs like seeds, fertilizers, pesticides, and to meet other agricultural expenses. ",
                "Financial Support",
                "Farmers",
                "https://www.pmkisan.gov.in/Documents/KCC.pdf "
            ),Scheme(
                "Atmanirbhar Krishi Yojana",
                "The Atmanirbhar Krishi Yojana focuses on boosting the agricultural infrastructure in India. The scheme aims to build modern storage facilities, cold storage units, and processing units to ensure that farmers have easy access to post-harvest management tools.",
                "Agricultural Infrastructure",
                "Farmers, agri-businesses, and rural entrepreneurs",
                "https://www.myscheme.gov.in/schemes/anky"
            ),Scheme(
                "National Food Security Mission (NFSM)",
                "The National Food Security Mission (NFSM) aims to increase the production of food grains (rice, wheat, pulses, and coarse cereals) to ensure food security in the country. ",
                "Food Security and Development",
                "Farmers, agricultural extension workers, and institutions",
                "https://www.nfsm.gov.in/"
            ))

        // Initialize the adapter
        adapter = SchemeAdapter(dummySchemes)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Optional: Setup toolbar back button
        findViewById<ImageView>(R.id.icon_menu).setOnClickListener {
            onBackPressed()
        }
    }
}
