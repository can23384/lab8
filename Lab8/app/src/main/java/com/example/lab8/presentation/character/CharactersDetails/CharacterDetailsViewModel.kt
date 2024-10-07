package com.example.lab8.presentation.character.CharactersDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.lab8.CharactersDetails.CharacterDetailsDestination
import com.example.lab8.LocationDetail.LocationDetailsDestination
import com.example.lab8.characters.CharacterDb
import com.example.lab8.presentation.location.LocationDetail.LocationDetailState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val characterDb = CharacterDb()
    private val CharacterProfile = savedStateHandle.toRoute<CharacterDetailsDestination>()
    private val _uiState: MutableStateFlow<CharactersDetailState> = MutableStateFlow(
        CharactersDetailState()
    )
    val uiState = _uiState.asStateFlow()

    private var shouldContinueLoading = true

    fun getCharacterData() {

        shouldContinueLoading = true
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true, hasError = false)
            }

            try {
                delay(2000)

                if (!shouldContinueLoading) return@launch

                val location = characterDb.getCharacterById(CharacterProfile.characterId)

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
        getCharacterData()
    }

    fun setErrorState() {
        shouldContinueLoading = false

        _uiState.update { state ->
            state.copy(hasError = true, isLoading = false)
        }
    }


}

