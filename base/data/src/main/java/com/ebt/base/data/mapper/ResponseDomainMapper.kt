package com.ebt.base.data.mapper

import com.ebt.base.data.model.ResponseModel
import com.ebt.base.domain.DomainModel

interface ResponseDomainMapper<R : ResponseModel, D : DomainModel> {
    fun mapToDomainModel(responseModel: R): D
}