package com.example.lab8.DataStore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lab8.dataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//class DataStoreViewModel(
//    private val userPreferences: UserPreferences
//) : ViewModel() {
//
//    private val _state = MutableStateFlow(DataStoreScreenState())
//    val state = _state.asStateFlow()
//
//    // Función para cargar el nombre al iniciar la pantalla
//    init {
//        loadName()
//    }
//
//    private fun loadName() {
//        viewModelScope.launch {
//            val savedName = userPreferences.getValue("name")  // Cargamos el nombre desde DataStore
//            _state.update { it.copy(name = savedName ?: "") }  // Actualizamos el estado con el nombre cargado
//        }
//    }
//
//    fun onEvent(event: DataStoreScreenEvent) {
//        when (event) {
//            DataStoreScreenEvent.GetValueClick -> { getValueFromKey() }
//            is DataStoreScreenEvent.KeyChange -> {
//                _state.update { it.copy(key = event.key) }
//            }
//            is DataStoreScreenEvent.NameChange -> {
//                _state.update { it.copy(name = event.name) }
//            }
//            DataStoreScreenEvent.SaveName -> saveName()
//        }
//    }
//
//    private fun saveName() {
//        viewModelScope.launch {
//            userPreferences.setName(_state.value.name)
//        }
//    }
//
//    private fun getValueFromKey() {
//        viewModelScope.launch {
//            _state.update { it.copy(value = userPreferences.getValue(_state.value.key)) }
//        }
//    }
//
//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = checkNotNull(this[APPLICATION_KEY])
//                DataStoreViewModel(
//                    userPreferences = DataStoreUserPrefs(application.dataStore)
//                )
//            }
//        }
//    }
//}
