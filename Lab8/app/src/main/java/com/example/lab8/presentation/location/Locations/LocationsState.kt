package com.example.lab8.presentation.location.Locations

import Location


data class LocationsState (
    val isLoading: Boolean = false,
    val data:  List<Location>? = null,
    val hasError: Boolean = false
)