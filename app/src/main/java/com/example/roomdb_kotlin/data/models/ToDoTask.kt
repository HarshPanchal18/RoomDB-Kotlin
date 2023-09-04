package com.example.roomdb_kotlin.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdb_kotlin.data.models.Priority
import com.example.roomdb_kotlin.util.Constants.DB_TABLE

@Entity(tableName = DB_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)
