package com.example.lab8.BottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.lab8.CharacterNavGraph
import com.example.lab8.LocationNavGraph
import com.example.lab8.Locations.LocationsScreenDestination
import com.example.lab8.Profile.ProfileDestination
import com.example.lab8.characters.CharacterScreenDestination

data class BottomNavigationItem<T : Any>(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: T
)

val menuItems = listOf(
    BottomNavigationItem(
        title = "Characters",
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        destination = CharacterNavGraph
    ),
    BottomNavigationItem(
        title = "Locations",
        unselectedIcon = Icons.Outlined.LocationOn,
        selectedIcon = Icons.Filled.LocationOn,
        destination = LocationNavGraph
    ),
    BottomNavigationItem(
        title = "Profile",
        unselectedIcon = Icons.Outlined.Face,
        selectedIcon = Icons.Filled.Face,
        destination = ProfileDestination
    )
)

val topLevelDestinations = listOf(
    CharacterScreenDestination::class,
    LocationsScreenDestination::class,
    ProfileDestination::class
)