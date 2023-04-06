package com.androidtask.domin.usecase

import com.androidtask.common.Resource
import com.androidtask.domin.entities.RateEntity
import com.androidtask.domin.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class RateUseCase(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(
        value: String
    ): Flow<Resource<RateEntity>> = flow {
        try {
            emit(Resource.Loading())
            val response = currencyRepository.getRates(value)
            emit(Resource.Success(response, response.message))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}