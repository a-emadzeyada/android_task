package com.androidtask.data.remote.model.response

import com.google.gson.annotations.SerializedName



data class BaseResponse(
    @SerializedName("base_code")
    val baseCode: String,
    @SerializedName("documentation")
    val documentation: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("rates")
    val rates: Rates,
    @SerializedName("result")
    val result: String,
    @SerializedName("terms_of_use")
    val termsOfUse: String,
    @SerializedName("time_eol_unix")
    val timeEolUnix: Int,
    @SerializedName("time_last_update_unix")
    val timeLastUpdateUnix: Int,
    @SerializedName("time_last_update_utc")
    val timeLastUpdateUtc: String,
    @SerializedName("time_next_update_unix")
    val timeNextUpdateUnix: Int,
    @SerializedName("time_next_update_utc")
    val timeNextUpdateUtc: String
)