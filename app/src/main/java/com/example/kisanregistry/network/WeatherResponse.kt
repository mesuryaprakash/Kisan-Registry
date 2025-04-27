package com.example.kisanregistry.network
data class WeatherResponse(
    val name: String,
    val weather: List<Weather>,
    val main: Main
)

data class Weather(
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double
)