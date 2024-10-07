package com.example.lab8.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab8.presentation.character.characters.CharacterState
import com.example.lab8.presentation.character.characters.CharacterViewModel
import com.example.lab8.presentation.location.Locations.LocationsState
import com.example.lab8.presentation.location.Locations.LocationsViewModel

@Composable
fun CharacterScreenRoute(
    onCharClick: (Int) -> Unit,
    viewModel: CharacterViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getCharacterData()
    }

    CharacterScreen(
        onCharClick = onCharClick,
        state = state,
        onRetry = { viewModel.retryLoadingData() },
        onSetErrorState = { viewModel.setErrorState() }
    )

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterScreen(
    onCharClick: (Int) -> Unit,
    state: CharacterState,
    onRetry: () -> Unit,
    onSetErrorState: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TopAppBar(
            title = { Text("Characters") },
            colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            )
            )
        },

   /*     bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary, // Color de fondo similar al de la imagen
                contentColor = MaterialTheme.colorScheme.onPrimary, // Color del contenido (íconos y texto)
                //tonalElevation = 8.dp
            ) {
                menuItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.destination)
                                  },
                        label = { Text(text = item.title) },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }

            }
        }*/

    ){ innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().clickable(enabled = state.isLoading) {
                onSetErrorState()
            },
        ) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Cargando")
                        }
                    }

                }
                state.hasError -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        ErrorLayout(onRetry = onRetry)
                    }

                }
                state.data != null -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        items(state.data.size) { index ->
                            CharacterItem(character = state.data[index],  onCharClick = onCharClick)
                        }
                    }
                }
                else -> {
                    Text("No se pudo obtener la información de la locación.")
                }
            }
        }






    }

}


@Composable
private fun CharacterItem(character: Character, onCharClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //   .height(70.dp)
            .clickable { onCharClick(character.id) }
            .padding(bottom = 23.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {


            }

            Spacer(modifier = Modifier.width(16.dp))

            Column (modifier = Modifier.fillMaxWidth()){
                Text(
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${character.species} - ${character.status}",
                    fontSize = 17.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


@Composable
private fun ErrorLayout(onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Error al obtener listado de personajes.\nIntenta de nuevo",
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Reintentar")
        }
    }
}



