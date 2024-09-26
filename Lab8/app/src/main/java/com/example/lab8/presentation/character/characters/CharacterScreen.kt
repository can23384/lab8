package com.example.lab8.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lab8.BottomNavigation.BottomNavigationItem
import com.example.lab8.Profile.ProfileDestination

@Composable
fun CharacterScreenRoute(onCharClick: (Int) -> Unit) {
    CharacterScreen(onCharClick = onCharClick)

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterScreen(
    onCharClick: (Int) -> Unit,
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


        val characterDb = CharacterDb()
        val characters = characterDb.getAllCharacters()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(characters.size) { index ->
                CharacterItem(character = characters[index],  onCharClick = onCharClick)
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



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    CharacterScreen(
        onCharClick = { /* Handle character click */ },
    )
}

