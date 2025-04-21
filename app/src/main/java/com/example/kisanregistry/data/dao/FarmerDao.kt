package com.example.kisanregistry.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.kisanregistry.data.model.Farmer

@Dao
interface FarmerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFarmer(farmer: Farmer)

    @Delete
    suspend fun deleteFarmer(farmer: Farmer)

    @Query("SELECT * FROM farmers ORDER BY name ASC")
    fun getAllFarmers(): LiveData<List<Farmer>>
}
