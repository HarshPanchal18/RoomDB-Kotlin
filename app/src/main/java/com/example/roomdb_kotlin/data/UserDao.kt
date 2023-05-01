package com.example.roomdb_kotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdb_kotlin.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao { // contains the methods used for accessing database

    @Insert(onConflict = OnConflictStrategy.IGNORE) // ignore the new same user
    suspend fun AddUser(user: User)

    @Query("SELECT * from user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUsr(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table WHERE firstName LIKE :searchQuery OR lastName LIKE :searchQuery")
    suspend fun searchDatabase(searchQuery: String): Flow<List<User>>
}
