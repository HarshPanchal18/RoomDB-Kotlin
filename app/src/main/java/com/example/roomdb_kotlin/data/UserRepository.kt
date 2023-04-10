package com.example.roomdb_kotlin.data

import androidx.lifecycle.LiveData

// abstracts access to multiple data sources.
class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.AddUser(user)
    }
}
