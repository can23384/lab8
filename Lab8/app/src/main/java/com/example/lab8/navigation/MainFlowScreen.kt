package com.example.lab8

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.lab8.BottomNavigation.BottomNavigationItem
import com.example.lab8.BottomNavigation.topLevelDestinations
import com.example.lab8.Profile.ProfileDestination
import com.example.lab8.Profile.profileScreen
import com.example.lab8.login.LoginDestination
import com.example.lab8.login.loginScreen

@Composable
fun MainFlowScreen(
    onLogOutClick: () -> Unit,
    navController: NavHostController = rememberNavController()
){
    var bottomBarVisable by rememberSaveable {
        mutableStateOf(false)
    }

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    bottomBarVisable = if (currentDestination != null) {
        topLevelDestinations.any { destination ->
            currentDestination.hasRoute(destination)

        }
    } else{
        false
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisable,
        //        enter = slideInVertically(intialOffsetY = { it }),
        //        exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                NavigationBar {

                }
                BottomNavBar(
                    checkItemSelected = {destination ->
                        currentDestination?.hierarchy?.any{ it.hasRoute(destination :: class)} ?: false
                    },
                    onNavItemClick = { destination ->
                        navController.navigate(destination) {
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true

                        }

                    }

                )

            }
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination =  CharacterNavGraph,
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){
            characterGraph(navController)
            locationGraph(navController)
            profileScreen(
                onLogOutClick  = onLogOutClick
            )




        }

    }



}


