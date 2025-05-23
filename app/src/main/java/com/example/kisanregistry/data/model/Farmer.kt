package com.example.kisanregistry.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "farmers")
data class Farmer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val village: String,
    val landSize: Double
)