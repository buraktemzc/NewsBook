package com.ebt.base.data.mapper

import com.ebt.base.data.model.EntityModel
import com.ebt.base.data.model.ResponseModel

interface EntitiesMapper<R : ResponseModel, E : EntityModel> {
    fun mapToEntityList(responseList: List<R>): List<E>
}