package com.example.lab8.characters

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable




@Serializable
data object CharacterScreenDestination

@Serializable
data object game


//es la que llama a la ruta
fun NavController.navigateTocharacterScreen(
    destination: CharacterScreenDestination,
    navOptions: NavOptions? = null
)
{
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.characterScreen(
    onCharClick: (Int) -> Unit,

) {
    composable<CharacterScreenDestination> {
        CharacterScreenRoute(onCharClick = onCharClick)
    }
}
