package com.ebt.features.home_impl.mapper

import com.ebt.base.ui.UIMapper
import com.ebt.domain.model.NewsDomainModel
import com.ebt.features.home_impl.model.NewsUIModel
import javax.inject.Inject

class NewsUIMapper @Inject constructor() : UIMapper<NewsDomainModel, NewsUIModel> {
    override fun mapToUIModel(domainModel: NewsDomainModel): NewsUIModel =
        domainModel.let {
            NewsUIModel(
                rowId = it.rowId,
                userId = it.userId,
                id = it.id,
                title = it.title,
                description = it.description,
                userImageURL = it.imageURL,
                updated = it.updated
            )
        }
}