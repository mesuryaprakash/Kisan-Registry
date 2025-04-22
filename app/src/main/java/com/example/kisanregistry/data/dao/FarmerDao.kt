package com.example.kisanregistry.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.kisanregistry.data.model.farmer

@Dao
interface FarmerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarmer(farmer: farmer)

    @Delete
    suspend fun deleteFarmer(farmer: farmer)

    @Query("SELECT * FROM farmers ORDER BY name ASC")
    fun getAllFarmers(): LiveData<List<farmer>>
}