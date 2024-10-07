package com.example.lab8.Locations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LocationsScreenDestination

fun NavController.navigateToLocationScreen(
    destination: LocationsScreenDestination,
    navOptions: NavOptions? = null
)
{
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locationScreen(
    onLocClick: (Int) -> Unit,

    ) {
    composable<LocationsScreenDestination> {
        LocationsScreenRoute(onLocClick = onLocClick)
    }
}
