package com.androidtask.data.remote.repository

import android.util.Log
import com.androidtask.data.remote.mapper.RateMapper
import com.androidtask.data.remote.model.response.BaseResponse
import com.androidtask.data.remote.service.ApiServices
import com.androidtask.domin.entities.RateEntity
import com.androidtask.domin.repository.CurrencyRepository

class CurrencyDataRepository(
    private val rateMapper: RateMapper,
    private val apiServices: ApiServices
) :CurrencyRepository {
    override suspend fun getRates(value: String): RateEntity {
        return try {
            val response = apiServices.getRates(value)
            rateMapper.modelToEntity(response)
        } catch (ex: Exception) {
            Log.i("API_SAVE_TOKEN", ex.message.toString())
            RateEntity()
        }
    }

}