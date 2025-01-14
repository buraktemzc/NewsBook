package com.ebt.base.data.mapper

import com.ebt.base.data.model.EntityModel
import com.ebt.base.data.model.ResponseModel

interface EntityMapper<R : ResponseModel, E : EntityModel> {
    fun mapToEntityModel(responseModel: R, index: Int): E
}