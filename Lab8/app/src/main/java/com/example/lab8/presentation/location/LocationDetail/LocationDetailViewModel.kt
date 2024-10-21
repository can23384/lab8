package com.example.lab8.presentation.location.LocationDetail

import LocationDb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.lab8.LocationDetail.LocationDetailsDestination
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationProfileViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val locationDb = LocationDb()
    private val locationProfile = savedStateHandle.toRoute<LocationDetailsDestination>()
    private val _uiState: MutableStateFlow<LocationDetailState> = MutableStateFlow(LocationDetailState())
    val uiState = _uiState.asStateFlow()

    private var shouldContinueLoading = true

    fun getLocationData() {

        shouldContinueLoading = true
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true, hasError = false)
            }

            try {
                delay(2000)

                if (!shouldContinueLoading) return@launch

                val location = locationDb.getLocationById(locationProfile.locationId)

                if (shouldContinueLoading) {
                    _uiState.update { state ->
                        state.copy(
                            data = location,
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
        getLocationData()
    }

    fun setErrorState() {
        shouldContinueLoading = false

        _uiState.update { state ->
            state.copy(hasError = true, isLoading = false)
        }
    }


}

