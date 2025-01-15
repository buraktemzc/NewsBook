package com.ebt.features.detail_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.core.model.Resource
import com.ebt.domain.usecase.GetNewsDetailUseCase
import com.ebt.domain.usecase.UpdateNewsUseCase
import com.ebt.features.home_impl.mapper.DetailUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getNewsDetailUseCase: GetNewsDetailUseCase,
    private val updateNewsUseCase: UpdateNewsUseCase,
    private val detailUIMapper: DetailUIMapper
) : ViewModel() {

    private val _detailFlow: MutableStateFlow<DetailEvent> = MutableStateFlow(DetailEvent.Loading)
    val detailFlow = _detailFlow.asStateFlow()

    private var currentRowId: Long = 0L

    fun getDetail(rowId: Long) = viewModelScope.launch {
        when (val result = getNewsDetailUseCase(rowId)) {
            is Resource.Success -> {
                val detail = result.value

                if (detail.rowId == 0L) {
                    // TODO: show no data
                    return@launch
                }

                currentRowId = detail.rowId
                _detailFlow.emit(DetailEvent.Show(detailUIMapper.mapToUIModel(detail)))
            }

            is Resource.Failure -> Unit
        }
    }

    fun update(title: String, description: String) = viewModelScope.launch {
        _detailFlow.emit(DetailEvent.Loading)
        yield()

        if (title == "" || description == "") {
            _detailFlow.emit(DetailEvent.NotUpdated)
            return@launch
        }

        if (currentRowId != 0L) {
            when (updateNewsUseCase(
                UpdateNewsUseCase.UpdateParam(
                    rowId = currentRowId,
                    title = title,
                    description = description
                )
            )) {
                is Resource.Success -> {
                    _detailFlow.emit(DetailEvent.Updated)
                }

                is Resource.Failure -> Unit
            }
        }
    }
}