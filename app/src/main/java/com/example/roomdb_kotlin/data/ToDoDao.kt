package com.example.roomdb_kotlin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomdb_kotlin.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

@Dao // Data Access Object - A class where you define database instructions
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasks()

    @Insert(onConflict = OnConflictStrategy.NONE) // Insert parameter into database
    suspend fun addTask(toDoTask: ToDoTask)

    @Update // will update its parameters in the database if they already exists (checked by PK). otherwise this option will not change the database.
    suspend fun updateTask(toDoTask: ToDoTask)

    @Delete // will delete its parameters from the database.
    suspend fun deleteTask(toDoTask: ToDoTask)

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchTask(searchQuery: String): Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): Flow<List<ToDoTask>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): Flow<List<ToDoTask>>
}
