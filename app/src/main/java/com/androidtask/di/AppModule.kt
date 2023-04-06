package com.androidtask.di

import android.app.Dialog
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val AppModule = module {
    single { Dialog(androidContext()) }
}