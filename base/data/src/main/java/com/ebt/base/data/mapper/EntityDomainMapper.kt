package com.ebt.base.data.mapper

import com.ebt.base.data.model.EntityModel
import com.ebt.base.domain.DomainModel

interface EntityDomainMapper<E : EntityModel, D : DomainModel> {
    fun mapToDomainModel(entity: E): D
}