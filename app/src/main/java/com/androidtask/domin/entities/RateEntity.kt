package com.androidtask.domin.entities

import com.androidtask.data.remote.model.response.Rates


data class RateEntity(
    val message: String = "",
    val rates: Rates? = null
)