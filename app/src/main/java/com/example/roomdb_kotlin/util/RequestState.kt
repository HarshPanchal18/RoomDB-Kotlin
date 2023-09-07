package com.example.roomdb_kotlin.util

sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Failure(val error: Throwable) : RequestState<Nothing>()

}
