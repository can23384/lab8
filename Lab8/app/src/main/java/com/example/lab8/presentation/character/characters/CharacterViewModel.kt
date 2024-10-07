package com.example.lab8.presentation.character.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab8.characters.CharacterDb
import com.example.lab8.presentation.location.Locations.LocationsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(

): ViewModel() {
    private val characterDb = CharacterDb()
    private val _uiState: MutableStateFlow<CharacterState> = MutableStateFlow(CharacterState())

    val uiState = _uiState.asStateFlow()

    private var shouldContinueLoading = true

    fun getCharacterData() {

        shouldContinueLoading = true
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true, hasError = false)
            }

            try {
                delay(4000)

                if (!shouldContinueLoading) return@launch

                val characters = characterDb.getAllCharacters()

                if (shouldContinueLoading) {
                    _uiState.update { state ->
                        state.copy(
                            data = characters,
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

