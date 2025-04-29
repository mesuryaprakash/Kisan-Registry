package com.example.kisanregistry.network
data class WeatherResponse(
    val name: String,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val rain: Rain? = null
)

data class Weather(
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val humidity: Int

)

data class Wind(
    val speed: Double  // Wind speed in meters per second
)

data class Rain(
    val `1h`: Int?  // Rain volume in the last 1 hour (if available)
)