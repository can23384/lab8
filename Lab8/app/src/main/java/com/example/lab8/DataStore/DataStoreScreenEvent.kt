package com.example.lab8.DataStore

sealed interface DataStoreScreenEvent {
    data object LogOut : DataStoreScreenEvent
    data class NameChange(val name: String) : DataStoreScreenEvent
    data object SaveName : DataStoreScreenEvent
}