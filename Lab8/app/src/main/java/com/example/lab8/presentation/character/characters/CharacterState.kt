package com.example.lab8.presentation.character.characters

import com.example.lab8.characters.Character


data class CharacterState (
    val isLoading: Boolean = false,
    val data:  List<Character>? = null,
    val hasError: Boolean = false
)