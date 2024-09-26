package com.example.lab8.LocationDetail

import LocationDb
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab8.characters.CharacterDb


@Composable
fun LocationDetailRoute(
    locationId: Int,
    onNavigateBack: () -> Unit

) {

    LocationDetailScreen(
        locationId = locationId, onNavigateBack = onNavigateBack
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    locationId: Int,
    onNavigateBack: () -> Unit
) {
    val locationDb = LocationDb()
    val LocationInfo = locationDb.getLocationById(locationId)

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,  // Alinear todos los elementos al centro

        ) {

            Text(
                text = LocationInfo.name,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )



            LocationDetailRow(label = "ID", value = LocationInfo.id.toString())
            LocationDetailRow(label = "Type", value = LocationInfo.type)
            LocationDetailRow(label = "Dimensions", value = LocationInfo.dimension)

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


