package com.example.lab8.Profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab8.DataStore.DataStoreScreenEvent
import com.example.lab8.DataStore.DataStoreScreenState
import com.example.lab8.R
import com.example.lab8.presentation.login.LoginViewModel

@Composable
fun ProfileRoute(
    onLogOutClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        state = state,
        onLogOutClick = onLogOutClick,
        CerrarSesion = {  viewModel.onEvent(DataStoreScreenEvent.LogOut) }
    )
}

@Composable
private fun ProfileScreen(
    state: DataStoreScreenState,
    onLogOutClick: () -> Unit,
    CerrarSesion: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ){
            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Logo Rick and Morty",
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Nombre:" ,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = state.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Carné:",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = "23384",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                onLogOutClick()
                CerrarSesion()
                      },
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Gray), // Borde gris
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, // Fondo blanco
                contentColor = Color(0xFF3F51B5) // Color del texto (azul)
            ),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Cerrar sesión")
        }
    }
}

