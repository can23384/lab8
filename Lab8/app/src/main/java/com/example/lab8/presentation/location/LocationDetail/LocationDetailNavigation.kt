package com.example.lab8.LocationDetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.lab8.characters.CharacterDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailsDestination(val locationId: Int)

fun NavController.navigateToLocationDetailScreen(
    destination: LocationDetailsDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.locationDetailScreen(
    onNavigateBack: () -> Unit
) {
    composable<LocationDetailsDestination> { backStackEntry ->
        val destination: LocationDetailsDestination = backStackEntry.toRoute()
        LocationDetailRoute(
            locationId = destination.locationId,
            onNavigateBack = onNavigateBack
        )
    }
}
//@Serializable
//data class LocationDetailsDestination(val locationId: Int)
//
//fun NavController.navigateToLocationDetailScreen(
//    locationId: Int,
//    navOptions: NavOptions? = null
//) {
//    this.navigate(
//        route = LocationDetailsDestination(locationId = locationId),
//        navOptions = navOptions
//    )
//}
//
//fun NavGraphBuilder.locationDetailScreen(
//    onNavigateBack: () -> Unit
//) {
//    composable<LocationDetailsDestination> {
//        LocationDetailRoute(
//            onNavigateBack = onNavigateBack
//        )
//    }
//}




