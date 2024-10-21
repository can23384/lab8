package com.example.lab8.DataStore

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun logIn()
    suspend fun logOut()
    fun authStatus(): Flow<Boolean>
    suspend fun setName(name: String)
    suspend fun getValue(key: String): String?
}