package com.example.kisanregistry

import android.R.attr.apiKey
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.kisanregistry.GridUI.ElecricityActivity
import com.example.kisanregistry.network.RetrofitClient
import com.example.kisanregistry.network.WeatherResponse
import com.example.kisanregistry.utils.LocationFetch
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Properties
import java.io.FileInputStream

class HomeDashboard : AppCompatActivity() {

    private val apiKey = "533392d6becd10adfa744922c0911172"  // Your OpenWeather API key

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var locationFetch: LocationFetch
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

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


        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        val menuIcon = findViewById<android.widget.ImageView>(R.id.icon_menu)
        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Access the header view
        val headerView = navigationView.getHeaderView(0)

// Get the references to the TextViews by ID
        val profileNameTextView = headerView.findViewById<TextView>(R.id.profile_name)
        val profileEmailTextView = headerView.findViewById<TextView>(R.id.profile_email)

// Get data from the Intent (if you passed the data from another activity)
        val profileName = intent.getStringExtra("NAME") ?: "User"
        val profileEmail = intent.getStringExtra("EMAIL") ?: "user@example.com"

// Set the values to the TextViews
        profileNameTextView.text = profileName
        profileEmailTextView.text = profileEmail





        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_language -> {
                    // Open Language settings
                }
                R.id.nav_track_active_complaints -> {
                    // Open Track Active Complaints
                }
                R.id.nav_view_past_complaints -> {
                    // Open View Past Complaints
                }
                R.id.nav_terms -> {
                    val intent = Intent(this, TermsAndConditionsActivity::class.java)
                    startActivity(intent)
                    // Open Terms and Conditions
                }
                R.id.nav_push_notification -> {
                    // Open Push Notifications
                }
                R.id.nav_help_support -> {
                    val intent = Intent(this, HelpAndSupportActivity::class.java)
                    startActivity(intent)
                    // Open Help and Support
                }

            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        var logoutBtn : ImageView = findViewById(R.id.icon_log_out)

        sharedPreferences = getSharedPreferences("KisanRegistryPrefs", Context.MODE_PRIVATE)

        // Get the username from the Intent
        val username = intent.getStringExtra("USERNAME")

        // Set up the logout button
        logoutBtn = findViewById(R.id.icon_log_out)

        logoutBtn.setOnClickListener {
            // Show the confirmation dialog
            showLogoutConfirmationDialog()
        }



        val elecricitybtn = findViewById<CardView>(R.id.elecricity_view)
        val elecricityIntent = Intent(this, ElecricityActivity::class.java)
        elecricitybtn.setOnClickListener{
            startActivity(elecricityIntent)
        }


        val soilQualitybtn = findViewById<CardView>(R.id.soilQualityCard)
        val soilIntent = Intent(this, SoilQualityActivity::class.java)

        soilQualitybtn.setOnClickListener{
            startActivity(soilIntent)
        }


        val schemsbtn = findViewById<CardView>(R.id.schemes_home)
        val schemeIntent = Intent(this, FarmerSchemes::class.java)

        schemsbtn.setOnClickListener{
            startActivity(schemeIntent)
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

                    true
                }
                R.id.nav_dashboard -> {

                    val schemesIntent = Intent(this, FarmerSchemes::class.java)
                    startActivity(schemesIntent)
                    true
                }
                R.id.nav_profile -> {
                    
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
                            val kelvinTemp = it.main.temp // Temperature in Kelvin
                            val celsiusTemp = kelvinTemp - 273.15

                            findViewById<TextView>(R.id.weather_status).text = it.weather[0].description
                            findViewById<TextView>(R.id.location_text).text = it.name
                            findViewById<TextView>(R.id.temprature_text).text = "${celsiusTemp.toInt()}°C"
                            findViewById<TextView>(R.id.humidity_text).text = "${it.main.humidity}%"
                            findViewById<TextView>(R.id.wind_speed_text).text = "${it.wind.speed} m/s"

                            // Rain data (if available)
                            val rainText = if (it.rain != null) {
                                "${it.rain.`1h`} %"
                            } else {
                                "0%"
                            }
                            findViewById<TextView>(R.id.rain_text).text = rainText


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



    fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to log out?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                // If the user confirms logout, clear SharedPreferences and navigate to login screen
                clearLoginState()
                val loginIntent = Intent(this, Login::class.java)
                startActivity(loginIntent)
                finish() // Close the HomeDashboard activity
            }
            .setNegativeButton("No") { dialog, id ->
                // If the user cancels logout, dismiss the dialog
                dialog.dismiss()
            }

        // Show the dialog
        builder.create().show()
    }

    fun clearLoginState() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", false)
        editor.remove("username")
        editor.apply()
    }
}