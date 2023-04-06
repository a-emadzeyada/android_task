package com.androidtask.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidtask.common.Resource
import com.androidtask.domin.entities.RateEntity
import com.androidtask.domin.usecase.RateUseCase
import com.androidtask.presentation.utility.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class RateViewModel(
    private val rateUseCase: RateUseCase
) : ViewModel() {
    private val _rates = MutableStateFlow<State<RateEntity>>(State())
    val rates: StateFlow<State<RateEntity>> = _rates

    fun getRates(value: String) {
        rateUseCase(value = value).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _rates.value = State(isLoading = true)
                }
                is Resource.Success -> {
                    _rates.value = State(
                        isLoading = false,
                        data = result.data
                    )
                }
                is Resource.Error -> {
                    _rates.value = State(
                        isLoading = false,
                        message = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}