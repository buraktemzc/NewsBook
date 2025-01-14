package com.ebt.newsbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebt.newsbook.model.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _destinationFlow: MutableStateFlow<Destination> = MutableStateFlow(Destination.Home)
    val destination = _destinationFlow.asStateFlow()

    fun goToHome() = viewModelScope.launch { _destinationFlow.emit(Destination.Home) }

    fun goToLastScreen() = viewModelScope.launch {
        _destinationFlow.emit(destination.value)
    }

    fun onNewsSelected(rowId: Long) =
        viewModelScope.launch { _destinationFlow.emit(Destination.Detail(rowId = rowId)) }
}