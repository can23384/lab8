package com.example.lab8

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.example.lab8.CharactersDetails.CharacterDetailsDestination
import com.example.lab8.CharactersDetails.characterDetailScreen
import com.example.lab8.CharactersDetails.navigateToCharacterDetailScreen
import com.example.lab8.LocationDetail.LocationDetailScreen
import com.example.lab8.LocationDetail.LocationDetailsDestination
import com.example.lab8.LocationDetail.locationDetailScreen
import com.example.lab8.LocationDetail.navigateToLocationDetailScreen
import com.example.lab8.Locations.LocationsScreenDestination
import com.example.lab8.Locations.LocationsScreenRoute
import com.example.lab8.Locations.locationScreen
import com.example.lab8.Locations.navigateToLocationScreen
import com.example.lab8.characters.CharacterScreenDestination
import com.example.lab8.characters.characterScreen
import com.example.lab8.characters.navigateTocharacterScreen
import kotlinx.serialization.Serializable

@Serializable
data object LocationNavGraph

fun NavController.navigateToLocationGraph(navOptions: NavOptions? = null) {
    this.navigate(LocationNavGraph, navOptions)
}

fun NavGraphBuilder.locationGraph(
    navController: NavController
) {
    navigation<LocationNavGraph>(
        startDestination = LocationsScreenDestination
    ) {
        locationScreen(
            onLocClick = { id ->
                navController.navigateToLocationDetailScreen(
                    destination = LocationDetailsDestination(locationId = id)
                )
            }
        )

        locationDetailScreen(
            onNavigateBack = {
                navController.navigateToLocationScreen(
                    destination = LocationsScreenDestination,
                    navOptions = navOptions {
                        popUpTo(0)
                    }
                )
            }
        )
    }
}