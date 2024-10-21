package com.example.lab8.presentation.login

sealed interface LoginStatus {
    data object Loading: LoginStatus
    data object Authenticated: LoginStatus
    data object NonAuthenticated: LoginStatus

}