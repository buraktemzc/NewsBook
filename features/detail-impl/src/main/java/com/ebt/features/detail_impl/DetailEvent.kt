package com.ebt.features.detail_impl

import com.ebt.features.detail_impl.model.DetailUIModel

sealed class DetailEvent {
    data class Show(val uiModel: DetailUIModel) : DetailEvent()
    data object NotUpdated : DetailEvent()
    data object Updated : DetailEvent()
    data object Loading : DetailEvent()
}