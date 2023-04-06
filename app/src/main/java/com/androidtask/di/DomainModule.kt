package com.androidtask.di

import com.androidtask.domin.usecase.RateUseCase
import org.koin.dsl.module


val DomainModule = module {
    factory { RateUseCase(get()) }
}