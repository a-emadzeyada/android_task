package com.androidtask

import android.app.Application
import com.androidtask.common.SharedPreferencesUtils
import com.androidtask.di.*
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesUtils.initializeSharedPrefsService(applicationContext)

        startKoin {
            modules(
                listOf(
                    AppModule, DataModule, DomainModule, ViewModelModule, DispatchersModule
                )
            )
        }
    }
}