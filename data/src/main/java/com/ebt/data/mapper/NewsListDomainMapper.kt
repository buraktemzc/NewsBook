package com.ebt.data.mapper

import com.ebt.base.data.mapper.EntitiesDomainListMapper
import com.ebt.data.model.entity.NewsEntity
import com.ebt.domain.model.NewsDomainModel
import javax.inject.Inject

class NewsListDomainMapper @Inject constructor(private val mapper: NewsDomainMapper) :
    EntitiesDomainListMapper<NewsEntity, NewsDomainModel> {
    override fun mapToDomainModel(entityList: List<NewsEntity>): List<NewsDomainModel> =
        entityList.map { mapper.mapToDomainModel(it) }
}