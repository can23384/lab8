package com.example.lab8.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import com.example.lab8.DataStore.DataStoreScreenEvent
import com.example.lab8.DataStore.DataStoreScreenState
import com.example.lab8.DataStore.DataStoreUserPrefs
import com.example.lab8.DataStore.UserPreferences
import com.example.lab8.characters.CharacterDb
import com.example.lab8.dataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userPreferences: UserPreferences,
) : ViewModel() {

    private val _state = MutableStateFlow(DataStoreScreenState())
    val state = _state.asStateFlow()

    // Al inicializar, verificamos si el usuario está logueado
    init {
        checkIfLoggedIn()  // Verificamos el estado de login al iniciar
    }

    private fun checkIfLoggedIn() {
        viewModelScope.launch {
            // Obtenemos el nombre guardado desde DataStore
            val savedName = userPreferences.getValue("name")

            if (!savedName.isNullOrEmpty()) {
                // Si el nombre existe, actualizamos el estado y marcamos al usuario como logueado
                _state.update { it.copy(name = savedName, isLoggedIn = true) }

                // Debug para confirmar que el nombre se recuperó correctamente
                println("Nombre recuperado: $savedName")
                println("Estado de logueo actualizado: ${_state.value.isLoggedIn}")
            } else {
                // Si no existe el nombre, actualizamos el estado como no logueado
                _state.update { it.copy(isLoggedIn = false) }
                println("Usuario no logueado, nombre no encontrado.")
            }
        }
    }

    fun onEvent(event: DataStoreScreenEvent) {
        when (event) {
            is DataStoreScreenEvent.NameChange -> {
                _state.update { it.copy(name = event.name) }
            }
            DataStoreScreenEvent.SaveName -> saveName()
            DataStoreScreenEvent.LogOut -> logOut()
        }
    }

    private fun saveName() {
        viewModelScope.launch {
            // Guarda el nombre en DataStore
            userPreferences.setName(_state.value.name)

            // Marca que el usuario está logueado
            userPreferences.logIn()

            // Actualizamos el estado
            _state.update { it.copy(isLoggedIn = true) }

            // Debug para verificar que el nombre se ha guardado y el usuario está logueado
            println("Nombre guardado: ${_state.value.name}")
            println("Usuario logueado: ${_state.value.isLoggedIn}")
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            userPreferences.setName("")  // Elimina el nombre de DataStore
            userPreferences.logOut()  // Marca que el usuario ha cerrado sesión
            _state.update { it.copy(name = "", isLoggedIn = false) }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // DataStore necesita un Context para ser accedido, por eso usamos application
                val application = checkNotNull(this[APPLICATION_KEY])
                LoginViewModel(
                    userPreferences = DataStoreUserPrefs(application.dataStore)  // Instanciamos el DataStoreUserPrefs
                )
            }
        }
    }

}