package com.androidtask.data.remote.mapper

import com.androidtask.data.remote.model.response.BaseResponse
import com.androidtask.domin.entities.RateEntity
import com.google.gson.Gson
import com.saifzone.smartapprovalsystem.data.remote.mapper.BaseModelMapper

class RateMapper:BaseModelMapper<BaseResponse,RateEntity> {
    override fun modelToEntity(dto: BaseResponse): RateEntity {
        return RateEntity(
            rates = dto.rates,
            message = dto.result
        )
    }
}