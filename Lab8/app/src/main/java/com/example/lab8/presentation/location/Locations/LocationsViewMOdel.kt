package com.example.lab8.presentation.location.Locations

import LocationDb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.lab8.LocationDetail.LocationDetailsDestination
import com.example.lab8.presentation.location.LocationDetail.LocationDetailState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationsViewModel(

): ViewModel() {
    private val locationDb = LocationDb()
    private val _uiState: MutableStateFlow<LocationsState> = MutableStateFlow(LocationsState())

    val uiState = _uiState.asStateFlow()

    private var shouldContinueLoading = true

    fun getLocationsData() {

        shouldContinueLoading = true
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true, hasError = false)
            }

            try {
                delay(4000)

                if (!shouldContinueLoading) return@launch

                val locations = locationDb.getAllLocations()

                if (shouldContinueLoading) {
                    _uiState.update { state ->
                        state.copy(
                            data = locations,
                            isLoading = false,
                            hasError = false
                        )
                    }
                }

            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        hasError = true,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun retryLoadingData() {
        getLocationsData()
    }

    fun setErrorState() {
        shouldContinueLoading = false

        _uiState.update { state ->
            state.copy(hasError = true, isLoading = false)
        }
    }


}

