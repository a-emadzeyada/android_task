package com.androidtask.di

import com.androidtask.common.DispatcherProvider
import com.androidtask.common.DispatcherProviderImpl
import org.koin.dsl.module


val DispatchersModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
}