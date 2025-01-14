package com.ebt.features.home_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.core.model.NewsError
import com.ebt.core.model.Resource
import com.ebt.domain.usecase.FetchNewsUseCase
import com.ebt.domain.usecase.GetNewsFromDBUseCase
import com.ebt.domain.usecase.GetNumberOfNewsUseCase
import com.ebt.features.home_impl.mapper.NewsUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase,
    private val getNewsFromDBUseCase: GetNewsFromDBUseCase,
    private val getNumberOfNewsUseCase: GetNumberOfNewsUseCase,
    private val newsUIMapper: NewsUIMapper
) : ViewModel() {

    private val _homeFlow: MutableStateFlow<HomeEvent> = MutableStateFlow(HomeEvent.Loading)
    val homeFlow = _homeFlow.asStateFlow()

    fun needToRunInitialOperations() {
        homeFlow.value.let { event ->
            when (event) {
                is HomeEvent.Failure -> {
                    when (event.error) {
                        is NewsError.ConnectionError, is NewsError.TimeOutException -> {
                            fetNews()
                        }

                        else -> Unit
                    }
                }

                else -> Unit
            }
        }
    }

    fun checkNewsAreReady() = viewModelScope.launch {
        when (val result = getNumberOfNewsUseCase(Unit)) {
            is Resource.Success -> {
                val numberOfNews = result.value
                if (numberOfNews == 0L) {
                    fetNews()
                    return@launch
                }
                getAllNewsFromDB()
            }

            is Resource.Failure -> Unit
        }
    }

    private fun fetNews() = viewModelScope.launch {
        when (val result = fetchNewsUseCase(Unit)) {
            is Resource.Success -> getAllNewsFromDB()
            is Resource.Failure -> _homeFlow.emit(HomeEvent.Failure(result.error))
        }
    }

    private fun getAllNewsFromDB() = viewModelScope.launch {
        getNewsFromDBUseCase(Unit)
            .onStart { _homeFlow.emit(HomeEvent.Loading) }
            .catch {
                _homeFlow.emit(
                    HomeEvent.Failure(
                        NewsError.ApiError(
                            "",
                            it.localizedMessage.orEmpty()
                        )
                    )
                )
            }
            .collect {
                _homeFlow.emit(HomeEvent.NewsRetrieved(it.map { item ->
                    newsUIMapper.mapToUIModel(item)
                }))
            }
    }
}