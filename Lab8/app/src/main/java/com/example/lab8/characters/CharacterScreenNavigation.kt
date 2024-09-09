package com.example.lab8.characters

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.lab8.CharactersDetails.CharacterDetailsDestination
import kotlinx.serialization.Serializable




@Serializable
data object CharacterScreenDestination

fun NavGraphBuilder.characterScreen(
    OnCharacterClick: (Character) -> Unit
) {
    composable<CharacterScreenDestination> {
        CharacterScreenRoute(onCharacterClick = OnCharacterClick)
    }
}
