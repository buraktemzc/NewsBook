package com.ebt.features.detail_impl.model

import com.ebt.base.ui.UIModel

data class DetailUIModel(
    val rowId: Long,
    val title: String,
    val description: String,
    val imageURL: String
) : UIModel
