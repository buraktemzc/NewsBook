package com.ebt.features.home_impl.mapper

import com.ebt.base.ui.UIMapper
import com.ebt.domain.model.NewsDomainModel
import com.ebt.features.detail_impl.model.DetailUIModel
import javax.inject.Inject

class DetailUIMapper @Inject constructor() : UIMapper<NewsDomainModel, DetailUIModel> {
    override fun mapToUIModel(domainModel: NewsDomainModel): DetailUIModel =
        domainModel.let {
            DetailUIModel(
                rowId = it.rowId,
                title = it.title,
                description = it.description,
                imageURL = it.imageURL
            )
        }
}