package com.example.kisanregistry.utils


import android.content.Context
import android.location.Location
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener

class LocationFetch(private val context: Context) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Method to check permission and request location
    fun checkPermissionAndFetchLocation(
        permissionLauncher: ActivityResultLauncher<String>,
        onLocationFetched: (Location) -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            // If permission granted, fetch location
            getCurrentLocation(onLocationFetched)
        } else {
            // If permission denied, request permission
            permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // Method to fetch the current location
    private fun getCurrentLocation(onLocationFetched: (Location) -> Unit) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        // Check if location permission is granted at runtime
        if (ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener(
                context as AppCompatActivity, OnSuccessListener { location: Location? ->
                    location?.let {
                        // Call the onLocationFetched callback with the location object
                        onLocationFetched(it)
                    } ?: run {
                        Toast.makeText(context, "Unable to get location", Toast.LENGTH_SHORT).show()
                    }
                })
        } else {
            // If permission is not granted, show a message or handle it gracefully
            Toast.makeText(context, "Location permission is required to fetch your location.", Toast.LENGTH_SHORT).show()
        }
    }

    // If permission is denied, show a Toast message
    fun showPermissionDeniedMessage() {
        Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
    }
}