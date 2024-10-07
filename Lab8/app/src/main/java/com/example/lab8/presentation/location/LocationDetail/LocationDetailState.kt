package com.example.lab8.presentation.location.LocationDetail

import Location

data class LocationDetailState (
    val isLoading: Boolean = false,
    val data: Location? = null,
    val hasError: Boolean = false
)