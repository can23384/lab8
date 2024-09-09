package com.example.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab8.CharactersDetails.CharacterDetailsDestination
import com.example.lab8.CharactersDetails.navigateToCharacterDetailScreen
import com.example.lab8.characters.CharacterScreenDestination
import com.example.lab8.characters.CharacterScreenRoute
import com.example.lab8.characters.characterScreen
import com.example.lab8.login.LoginDestination
import com.example.lab8.login.LoginRoute
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
                    ){
                        loginScreen(onEntrarClick = {navController.navigate(CharacterScreenDestination)})

                        characterScreen(
                            OnCharacterClick = { Character ->
                                navController.navigateToCharacterDetailScreen(
                                    destination = CharacterDetailsDestination(
                                        characterId = Character.id
                                    )
                                )
                            }


                        )


                    }



                }
            }
        }
    }


}

