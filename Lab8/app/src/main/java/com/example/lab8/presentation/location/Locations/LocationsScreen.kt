package com.example.lab8.Locations

import Location
import LocationDb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LocationsScreenRoute(onLocClick: (Int) -> Unit) {
    LocationsScreen(onLocClick = onLocClick)

}





@Composable
private fun LocationsScreen(
    onLocClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val locationDb = LocationDb()
    val locations = locationDb.getAllLocations()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(locations.size) { index ->
            LocationItem(location = locations[index], onLocClick = onLocClick)
        }
    }
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

