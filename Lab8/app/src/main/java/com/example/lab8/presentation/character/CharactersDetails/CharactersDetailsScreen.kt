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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import coil.compose.AsyncImage
import com.example.lab8.LocationDetail.LocationDetailRow
import com.example.lab8.presentation.character.CharactersDetails.CharacterDetailsViewModel
import com.example.lab8.presentation.character.CharactersDetails.CharactersDetailState
import com.example.lab8.presentation.location.LocationDetail.LocationDetailState
import com.example.lab8.ui.theme.Lab8Theme



@Composable
fun CharacterDetailRoute(
    CharacterId: Int,
    onNavigateBack: () -> Unit,
    viewModel: CharacterDetailsViewModel = viewModel()

) {
    LaunchedEffect(CharacterId) {
        viewModel.getCharacterData()
    }

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterDetailsScreen(
        CharacterId = CharacterId,
        state = state,
        onNavigationBack = onNavigateBack,
        onRetry = { viewModel.retryLoadingData() },
        onSetErrorState = { viewModel.setErrorState() }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterDetailsScreen(
    CharacterId: Int,
    state: CharactersDetailState,
    onNavigationBack: () -> Unit,
    onRetry: () -> Unit,
    onSetErrorState: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.fillMaxSize()
    )
    {
        TopAppBar(
            title = {
                Text("Character Detail")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            navigationIcon = {
                IconButton(onClick = onNavigationBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )

                }

            }
        )

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
                    CharacterId(state.data)
                }
                else -> {
                    Text("No se pudo obtener la información de la locación.")
                }
            }
        }




    }

}

@Composable
private fun CharacterId(idCharacter: Character) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )

    {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ){
      /*      AsyncImage(
                model = idCharacter.image,
                contentDescription = null,
            )*/
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = idCharacter.name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Species:",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                color = Color.Gray,
                textAlign = androidx.compose.ui.text.style.TextAlign.End
            )
            Spacer(modifier = Modifier.width(150.dp))
            Text(
                text = idCharacter.species,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )

        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Status:",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                color = Color.Gray,
                textAlign = androidx.compose.ui.text.style.TextAlign.End
            )
            Spacer(modifier = Modifier.width(150.dp))
            Text(
                text = idCharacter.status,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )

        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Gender:",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                color = Color.Gray,
                textAlign = androidx.compose.ui.text.style.TextAlign.End
            )
            Spacer(modifier = Modifier.width(150.dp))
            Text(
                text = idCharacter.gender,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )

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

