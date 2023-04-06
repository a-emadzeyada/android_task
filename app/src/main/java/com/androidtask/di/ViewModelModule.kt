package com.androidtask.di

import com.androidtask.presentation.main.RateViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val ViewModelModule = module {
    viewModel { RateViewModel(get()) }
}