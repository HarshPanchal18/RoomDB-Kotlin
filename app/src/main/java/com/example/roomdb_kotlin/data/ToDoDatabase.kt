package com.example.roomdb_kotlin.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb_kotlin.data.models.ToDoTask

// Marking a class as a RoomDatabase.
@Database(
    entities = [ToDoTask::class], // List of entities included in database, each entity turns into table
    version = 1, // DB version
    exportSchema = false
)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
