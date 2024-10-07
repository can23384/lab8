package com.example.lab8.presentation.character.CharactersDetails

import com.example.lab8.characters.Character

data class CharactersDetailState (
    val isLoading: Boolean = false,
    val data: Character? = null,
    val hasError: Boolean = false
)