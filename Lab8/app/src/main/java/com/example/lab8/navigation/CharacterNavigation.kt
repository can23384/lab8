package com.example.lab8

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.example.lab8.CharactersDetails.CharacterDetailsDestination
import com.example.lab8.CharactersDetails.characterDetailScreen
import com.example.lab8.CharactersDetails.navigateToCharacterDetailScreen
import com.example.lab8.characters.CharacterScreenDestination
import com.example.lab8.characters.characterScreen
import com.example.lab8.characters.navigateTocharacterScreen
import kotlinx.serialization.Serializable

@Serializable
data object CharacterNavGraph

fun NavController.navigateToCharacterGraph(navOptions: NavOptions? = null) {
    this.navigate(CharacterNavGraph, navOptions)
}

fun NavGraphBuilder.characterGraph(
    navController: NavController
) {
    navigation<CharacterNavGraph>(
        startDestination = CharacterScreenDestination
    ) {
        characterScreen(
            onCharClick = { id ->
                navController.navigateToCharacterDetailScreen(
                    destination = CharacterDetailsDestination(characterId = id)
                )
            }
        )

        characterDetailScreen(
            onNavigateBack = {
                navController.navigateTocharacterScreen(
                    destination = CharacterScreenDestination,
                    navOptions = navOptions {
                        popUpTo(0)
                    }
                )
            }
        )
    }
}