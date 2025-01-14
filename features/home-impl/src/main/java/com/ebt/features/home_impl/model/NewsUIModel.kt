package com.ebt.features.home_impl.model

import com.ebt.base.ui.UIModel

data class NewsUIModel(
    val rowId: Long,
    val userId: Long,
    val id: Long,
    val title: String,
    val description: String,
    val userImageURL: String,
    val updated: Boolean
) : UIModel
