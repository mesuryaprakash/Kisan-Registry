package com.example.kisanregistry.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kisanregistry.data.dao.FarmerDao
import com.example.kisanregistry.data.model.farmer

@Database(entities = [farmer::class], version = 1, exportSchema = false)
abstract class FarmerDatabase : RoomDatabase() {

    abstract fun farmerDao(): FarmerDao

    companion object {
        @Volatile
        private var INSTANCE: FarmerDatabase? = null

        fun getDatabase(context: Context): FarmerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FarmerDatabase::class.java,
                    "farmer_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
