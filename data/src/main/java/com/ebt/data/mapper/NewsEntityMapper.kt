package com.ebt.data.mapper

import com.ebt.base.data.mapper.EntityMapper
import com.ebt.data.model.entity.NewsEntity
import com.ebt.data.model.response.NewsResponse
import javax.inject.Inject

class NewsEntityMapper @Inject constructor() : EntityMapper<NewsResponse, NewsEntity> {
    override fun mapToEntityModel(responseModel: NewsResponse, index: Int): NewsEntity =
        NewsEntity(
            userId = responseModel.userId,
            id = responseModel.id,
            title = responseModel.title,
            body = responseModel.body,
            imageURL = "https://picsum.photos/300/300?random=$index&grayscale"
        )
}