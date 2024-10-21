package com.example.lab8.presentation.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab8.MainNavigationGraph
import com.example.lab8.login.LoginDestination
import com.example.lab8.login.loginScreen
import com.example.lab8.navigateToMainGraph
import com.example.lab8.ui.theme.Lab8Theme
import kotlinx.serialization.Serializable


@Serializable
data object DSSplashDestination

@Composable
fun DSAppContent(
    navController: NavController,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Verificando el estado de inicio de sesión...")
    }


    LaunchedEffect(Unit) {
        if (state.isLoggedIn) {
            println("Estado de login: ${state.isLoggedIn}")

            // Navegar a la pantalla de bienvenida
            // Navegar al login
            navController.navigate(LoginDestination) {
                popUpTo(0)
            }
        } else {
            navController.navigateToMainGraph(
                navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                    inclusive = true
                ).build()
            )

        }
    }
    // Mostrar algo mientras cargamos el estado de login
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Cargando...")
    }
}









