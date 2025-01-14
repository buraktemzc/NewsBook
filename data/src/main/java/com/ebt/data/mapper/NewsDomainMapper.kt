package com.ebt.data.mapper

import com.ebt.base.data.mapper.EntityDomainMapper
import com.ebt.data.model.entity.NewsEntity
import com.ebt.domain.model.NewsDomainModel
import javax.inject.Inject

class NewsDomainMapper @Inject constructor() : EntityDomainMapper<NewsEntity, NewsDomainModel> {
    override fun mapToDomainModel(entity: NewsEntity): NewsDomainModel =
        NewsDomainModel(
            rowId = entity.rowId,
            id = entity.id ?: 0L,
            userId = entity.userId ?: 0L,
            title = entity.title ?: "",
            description = entity.body ?: "",
            imageURL = entity.imageURL,
            updated = entity.updated
        )
}