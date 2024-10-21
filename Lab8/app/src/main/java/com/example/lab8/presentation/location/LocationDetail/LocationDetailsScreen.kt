package com.example.lab8.LocationDetail

import Location
import LocationDb
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
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
import com.example.lab8.characters.CharacterDb
import com.example.lab8.presentation.location.LocationDetail.LocationDetailState
import com.example.lab8.presentation.location.LocationDetail.LocationProfileViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun LocationDetailRoute(
    locationId: Int,
    onNavigateBack: () -> Unit,
    viewModel: LocationProfileViewModel = viewModel()

) {

    LaunchedEffect(locationId) {
        viewModel.getLocationData()
    }
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LocationDetailScreen(
        locationId = locationId,
        state = state,
        onNavigateBack = onNavigateBack,
        onRetry = { viewModel.retryLoadingData() },
        onSetErrorState = { viewModel.setErrorState() }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    locationId: Int,
    state: LocationDetailState,
    onNavigateBack: () -> Unit,
    onRetry: () -> Unit,
    onSetErrorState: () -> Unit
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Location Details") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().clickable(enabled = state.isLoading) {
                onSetErrorState()
            },
        ) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
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
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ){
                        ErrorLayout(onRetry = onRetry)
                    }

                }
                state.data != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {

                        Text(
                            text = state.data.name,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        LocationDetailRow(label = "ID", value = state.data.id.toString())
                        LocationDetailRow(label = "Type", value = state.data.type)
                        LocationDetailRow(label = "Dimensions", value = state.data.dimension)

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


@Composable
fun LocationDetailRow(label: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 65.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.End
            )
        }
    }
}


