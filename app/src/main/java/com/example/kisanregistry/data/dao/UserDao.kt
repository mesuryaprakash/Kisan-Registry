package com.example.kisanregistry.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kisanregistry.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): LiveData<User?>
}
