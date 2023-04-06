package com.androidtask.presentation.utility

data class State<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val message: String? = ""
)
