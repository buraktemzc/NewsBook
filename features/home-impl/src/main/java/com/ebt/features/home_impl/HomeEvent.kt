package com.ebt.features.home_impl

import com.ebt.core.model.NewsError
import com.ebt.features.home_impl.model.NewsUIModel

sealed class HomeEvent {
    data class NewsRetrieved(val list: List<NewsUIModel>) : HomeEvent()
    data object ItemRemoved : HomeEvent()
    data object ShowEmptyView : HomeEvent()
    class Failure(val error: NewsError) : HomeEvent()
    data object Loading : HomeEvent()
}