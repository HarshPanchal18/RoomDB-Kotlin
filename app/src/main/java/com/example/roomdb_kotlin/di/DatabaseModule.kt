package com.example.roomdb_kotlin.di

import android.content.Context
import androidx.room.Room
import com.example.roomdb_kotlin.data.ToDoDatabase
import com.example.roomdb_kotlin.util.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context = context,
            klass = ToDoDatabase::class.java,
            name = DB_NAME
        ).build()

    @Singleton
    @Provides // To return feature of each module method.
    fun provideDao(database: ToDoDatabase) = database.toDoDao()
}
