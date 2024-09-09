package com.example.lab8.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.lab8.characters.CharacterScreenDestination
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(onEntrarClick: () -> Unit) {
    composable<LoginDestination> {
        LoginRoute(onEntrarClick = onEntrarClick)
    }
}

