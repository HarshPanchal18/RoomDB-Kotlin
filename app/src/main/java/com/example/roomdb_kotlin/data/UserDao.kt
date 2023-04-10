package com.example.roomdb_kotlin.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao { // contains the methods used for accessing database

    @Insert(onConflict = OnConflictStrategy.IGNORE) // ignore the new same user
    suspend fun AddUser(user: User)

    @Query("SELECT * from user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}