package com.androidtask.domin.repository

import com.androidtask.domin.entities.RateEntity

interface CurrencyRepository {
    suspend fun getRates(value: String): RateEntity

}