package com.example.roomdb_kotlin.repository

import androidx.lifecycle.LiveData
import com.example.roomdb_kotlin.data.UserDao
import com.example.roomdb_kotlin.model.User

// abstracts access to multiple data sources.
class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.AddUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUsr(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAll()
    }
}
