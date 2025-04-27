package com.example.kisanregistry

import android.R.attr.apiKey
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.kisanregistry.network.RetrofitClient
import com.example.kisanregistry.network.WeatherResponse
import com.example.kisanregistry.utils.LocationFetch
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Properties
import java.io.FileInputStream

class HomeDashboard : AppCompatActivity() {

    private val apiKey = "533392d6becd10adfa744922c0911172"  // Your OpenWeather API key


    private lateinit var locationFetch: LocationFetch

    // ActivityResultLauncher to request location permission
    private lateinit var requestLocationPermission: ActivityResultLauncher<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_dashboard)

        // Initialize the LocationFetch class
        locationFetch = LocationFetch(this)

        // Initialize the requestLocationPermission launcher
        requestLocationPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                locationFetch.checkPermissionAndFetchLocation(
                    requestLocationPermission,
                    ::fetchWeatherData // Pass fetchWeatherData as callback
                )
            } else {
                locationFetch.showPermissionDeniedMessage()
            }
        }

        // Check permission and fetch location
        checkLocationPermission()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Hide default title

        val weatherStatus: TextView = findViewById(R.id.weather_status)
        val locationText: TextView = findViewById(R.id.location_text)
        val weatherIcon: ImageView = findViewById(R.id.weather_icon)
        val temperature: TextView = findViewById(R.id.temprature_text)

        val userName = intent.getStringExtra("USERNAME") ?: "User"
        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = "Hi, $userName"

        val menuIcon: ImageView = findViewById(R.id.icon_menu)
        menuIcon.setOnClickListener {
            // Open drawer or perform some action
        }

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.white)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_dashboard -> {
                    Toast.makeText(this, "Dashboard clicked", Toast.LENGTH_SHORT).show()
                    val schemesIntent = Intent(this, FarmerSchemes::class.java)
                    startActivity(schemesIntent)
                    true
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
                    val userIntent = Intent(this, UserProfile::class.java)
                    startActivity(userIntent)
                    true
                }
                else -> false
            }
        }
    }

    private fun checkLocationPermission() {
        when {
            // Check if the permission is already granted
            androidx.core.content.ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED -> {
                // Permission granted, fetch the location
                locationFetch.checkPermissionAndFetchLocation(requestLocationPermission, ::fetchWeatherData)
            }
            else -> {
                // Permission is not granted, request permission
                requestLocationPermission.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun fetchWeatherData(location: Location) {
        // Fetch weather data using Retrofit
        RetrofitClient.api.getWeatherByCoordinates(location.latitude, location.longitude, apiKey)
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weatherData = response.body()
                        weatherData?.let {
                            findViewById<TextView>(R.id.weather_status).text = it.weather[0].description
                            findViewById<TextView>(R.id.location_text).text = it.name
                            findViewById<TextView>(R.id.temprature_text).text = "${it.main.temp}°C"

                            val iconUrl = "https://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png"
                            Glide.with(this@HomeDashboard)
                                .load(iconUrl)
                                .into(findViewById(R.id.weather_icon))
                        }

                        val weatherArrow: ImageView = findViewById(R.id.arrow_icon)
                        weatherArrow.setOnClickListener {
                            // Construct the URL for the weather website, using location coordinates
                            val latitude = location.latitude
                            val longitude = location.longitude
                            val url = "https://www.weather.com/weather/tenday/l/$latitude,$longitude"

                            // Open the URL in a browser
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            startActivity(intent)
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@HomeDashboard, "Failed to load weather data", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
