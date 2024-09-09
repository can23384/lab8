package com.example.lab8.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab8.R
import com.example.lab8.ui.theme.Lab8Theme

@Composable
fun LoginRoute(
    onEntrarClick: () -> Unit
){
    Login(
        onEntrarClick = onEntrarClick
    )
}

@Composable
private fun Login(
    onEntrarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.rickandmorty),
                contentDescription = "Logo Rick and Morty",
            )


            Button(
                onClick = {onEntrarClick()},
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = "Entrar")
            }



        }

        Text(
            text = "Eliazar Canastuj - #23384",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter)
        )
    }


}


