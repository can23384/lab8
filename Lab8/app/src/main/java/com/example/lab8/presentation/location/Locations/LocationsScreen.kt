package com.example.lab8.Locations

import Location
import LocationDb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.lab8.presentation.location.LocationDetail.LocationDetailState
import com.example.lab8.presentation.location.LocationDetail.LocationProfileViewModel
import com.example.lab8.presentation.location.Locations.LocationsState
import com.example.lab8.presentation.location.Locations.LocationsViewModel

@Composable
fun LocationsScreenRoute(
    onLocClick: (Int) -> Unit,
    viewModel: LocationsViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getLocationsData()
    }
    LocationsScreen(
        onLocClick = onLocClick,
        state = state,
        onRetry = { viewModel.retryLoadingData() },
        onSetErrorState = { viewModel.setErrorState() }
    )

}





@Composable
private fun LocationsScreen(
    onLocClick: (Int) -> Unit,
    state: LocationsState,
    onRetry: () -> Unit,
    onSetErrorState: () -> Unit,
    modifier: Modifier = Modifier
){
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
                        .padding(8.dp)
                ) {
                    items(state.data.size) { index ->
                        LocationItem(location = state.data[index], onLocClick = onLocClick)
                    }
                }
            }
            else -> {
                Text("No se pudo obtener la información de la locación.")
            }
        }

    }
   // val locationDb = LocationDb()
  //  val locations = locationDb.getAllLocations()


}


@Composable
private fun LocationItem(location: Location, onLocClick: (Int) -> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //   .height(70.dp)
            .clickable { onLocClick(location.id) }
            .padding(bottom = 40.dp)
    ) {
        Column (modifier = Modifier.fillMaxWidth()){
            Text(
                text = location.name,
                fontSize = 25.sp,
            )
            Text(
                text = location.type,
                fontSize = 18.sp,
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

