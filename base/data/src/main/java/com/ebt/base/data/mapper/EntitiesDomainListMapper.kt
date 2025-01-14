package com.ebt.base.data.mapper

import com.ebt.base.data.model.EntityModel
import com.ebt.base.domain.DomainModel

interface EntitiesDomainListMapper<E : EntityModel, D : DomainModel> {
    fun mapToDomainModel(entityList: List<E>): List<D>
}