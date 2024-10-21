package com.example.lab8.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab8.DataStore.DataStoreScreenEvent
import com.example.lab8.DataStore.DataStoreScreenState
import com.example.lab8.R
import com.example.lab8.characters.CharacterScreenRoute
import com.example.lab8.presentation.login.LoginViewModel
import com.example.lab8.ui.theme.Lab8Theme


@Composable
fun LoginRoute(
    onEntrarClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory),

    ){


    val state by viewModel.state.collectAsStateWithLifecycle()

    Login(
        state = state,
        onEntrarClick = onEntrarClick,
        onNameChange = { viewModel.onEvent(DataStoreScreenEvent.NameChange(it)) },
        onSaveNameClick = { viewModel.onEvent(DataStoreScreenEvent.SaveName) },
    )
}

@Composable
private fun Login(
    state: DataStoreScreenState,
    onEntrarClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onSaveNameClick: () -> Unit,
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


            OutlinedTextField(
                value = state.name,
                onValueChange = onNameChange,
                placeholder = {
                    Text("Nombre")
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {onEntrarClick()
                    onSaveNameClick()
                },
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


@Preview
@Composable
private fun PreviewDataStoreScreen() {
    Lab8Theme() {
        Surface {
            LoginRoute(
                onEntrarClick = { }
            )
        }
    }
}