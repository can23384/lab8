package com.example.lab8.Profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.lab8.login.LoginDestination
import com.example.lab8.login.LoginRoute
import com.example.lab8.navigateToMainGraph
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(onLogOutClick: () -> Unit) {
    composable<ProfileDestination> {
        ProfileRoute(onLogOutClick = onLogOutClick)
    }
}
