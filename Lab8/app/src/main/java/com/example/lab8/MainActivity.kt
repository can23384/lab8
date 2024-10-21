package com.example.lab8

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.example.lab8.CharactersDetails.CharacterDetailsDestination
import com.example.lab8.CharactersDetails.characterDetailScreen
import com.example.lab8.CharactersDetails.navigateToCharacterDetailScreen
import com.example.lab8.Profile.profileScreen
import com.example.lab8.characters.CharacterScreenDestination
import com.example.lab8.characters.characterScreen
import com.example.lab8.characters.game
import com.example.lab8.characters.navigateTocharacterScreen
import com.example.lab8.login.LoginDestination
import com.example.lab8.login.loginScreen
import com.example.lab8.presentation.login.DSAppContent
import com.example.lab8.presentation.login.DSSplashDestination
import com.example.lab8.ui.theme.Lab8Theme

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()




        setContent {
            Lab8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
//                    val navController = rememberNavController()
//                    // Cargar la navegación con la pantalla splash como la principal
//                    NavHost(navController = navController, startDestination = DSSplashDestination) {
//                        composable<DSSplashDestination> {
//                            DSAppContent(navController = navController)
//
//
//                        }
//
//                        composable<LoginDestination> {
//                            loginScreen(
//                                onEntrarClick  = {
//                                    navController.navigateToMainGraph(
//                                        navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
//                                            inclusive = true
//                                        ).build()
//                                    )
//                                }
//                            )
//
//                        }
//
//                        composable<MainNavigationGraph>{
//                            MainNavigationGraph(
//                                onLogOutClick = {
//                                    navController.navigate(LoginDestination){
//                                        popUpTo(0)
//                                    }
//                                }
//                            )
//                        }
//                    }
                  //  DSAppContent()
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = LoginDestination,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        loginScreen(
                            onEntrarClick  = {
                                navController.navigateToMainGraph(
                                    navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                                        inclusive = true
                                    ).build()
                                )
                            }
                        )

                        MainNavigationGraph(
                            onLogOutClick = {
                                navController.navigate(LoginDestination){
                                    popUpTo(0)
                                }
                            }
                        )



                    }
                }
            }
        }
    }


}


