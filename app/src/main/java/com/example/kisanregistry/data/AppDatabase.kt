package com.example.kisanregistry.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kisanregistry.data.dao.FarmerDao
import com.example.kisanregistry.data.dao.UserDao
import com.example.kisanregistry.data.model.Farmer
import com.example.kisanregistry.data.model.User

@Database(entities = [Farmer::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun farmerDao(): FarmerDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kisan_registry_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
