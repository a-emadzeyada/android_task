package com.androidtask.di

import com.androidtask.data.remote.mapper.RateMapper
import com.androidtask.data.remote.repository.CurrencyDataRepository
import com.androidtask.data.remote.service.*
import com.androidtask.domin.repository.CurrencyRepository
import org.koin.dsl.module


val DataModule = module {
    factory {
        provideOkHttpClient(
            get(), provideSSLContext(provideX509TrustManager()), provideX509TrustManager()
        )
    }
    factory { provideLoggingInterceptor() }

    factory { provideRetrofit(get()) }

    factory { provideAppAPI(get()) }


    //repositories
    factory<CurrencyRepository> {
        CurrencyDataRepository(
            get(), get()
        )
    }

    /**
     * MAPPER
     * **/
    single { RateMapper() }
}