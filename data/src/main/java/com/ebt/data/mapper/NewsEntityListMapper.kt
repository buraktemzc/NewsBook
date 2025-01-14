package com.ebt.data.mapper

import com.ebt.base.data.mapper.EntitiesMapper
import com.ebt.data.model.entity.NewsEntity
import com.ebt.data.model.response.NewsResponse
import javax.inject.Inject

class NewsEntityListMapper @Inject constructor(private val entityMapper: NewsEntityMapper) :
    EntitiesMapper<NewsResponse, NewsEntity> {
    override fun mapToEntityList(responseList: List<NewsResponse>): List<NewsEntity> {
        return responseList.mapIndexed { index, item -> entityMapper.mapToEntityModel(item, index) }
    }
}