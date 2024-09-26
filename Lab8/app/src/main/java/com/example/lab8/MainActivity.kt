package com.example.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
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
import com.example.lab8.ui.theme.Lab8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
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


