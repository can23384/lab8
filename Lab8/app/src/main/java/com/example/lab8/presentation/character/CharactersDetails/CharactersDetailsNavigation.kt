package com.example.lab8.CharactersDetails

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.lab8.characters.CharacterDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailsDestination(val characterId: Int)

fun NavController.navigateToCharacterDetailScreen(
    destination: CharacterDetailsDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.characterDetailScreen(
    onNavigateBack: () -> Unit
) {
    composable<CharacterDetailsDestination> { backStackEntry ->
        val destination: CharacterDetailsDestination = backStackEntry.toRoute()
        CharacterDetailRoute(
            CharacterId = destination.characterId,
            onNavigateBack = onNavigateBack
        )
    }
}

