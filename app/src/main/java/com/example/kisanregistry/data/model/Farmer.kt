package com.example.kisanregistry.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "farmers",
//    foreignKeys = [ForeignKey(
//        entity = User::class,
//        parentColumns = ["id"],
//        childColumns = ["userId"],
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class Farmer(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val name: String?,
    val address: String?,
    val phone: String?,
    val village: String?,
    val landSize: Double?,
    val aadhaar: String?,
)