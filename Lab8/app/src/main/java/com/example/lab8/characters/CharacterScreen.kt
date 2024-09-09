package com.example.lab8.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CharacterScreenRoute(onCharacterClick: (Character) -> Unit) {
    CharacterScreen(onCharacterClick = onCharacterClick)
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterScreen(
    onCharacterClick: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Characters") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            )
        )

        val characterDb = CharacterDb()
        val characters = characterDb.getAllCharacters()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(characters.size) { index ->
                CharacterItem(character = characters[index], onCharacterClick)
            }
        }
    }
}

@Composable
private fun CharacterItem(character: Character, onCharacterClick: (Character) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onCharacterClick(character) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                )

            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${character.species} - ${character.status}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}



/*private fun CharacterScreen(
    onCharacterClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier.fillMaxSize()){
        TopAppBar(
            title = {
                Text("Characters")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            )
        )

        val characterDb = CharacterDb()
        val characters = characterDb.getAllCharacters()

        CharacterList(characters, onCharacterClick)


    }
}



@Composable
private fun CharacterList(characters: List<Character>, onCharacterClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(characters.size) { index ->
            CharacterItem(character = characters[index], onCharacterClick)
        }
    }
}




@Composable
private fun CharacterItem(character: Character, onCharacterClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onCharacterClick() }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),


            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ){
                /*        AsyncImage(
                            model = character.image,
                            contentDescription = null,
                        )*/
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${character.species} - ${character.status}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

    }

}
*/

