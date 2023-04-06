package com.saifzone.smartapprovalsystem.data.remote.mapper

interface BaseModelMapper<M, E> {
    fun modelToEntity(dto: M): E
}