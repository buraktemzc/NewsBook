package com.ebt.network.model

sealed class NetworkResponse<T> {
    data class Success<T>(val body: T) : NetworkResponse<T>()
    data class Failure<T>(val error: Error) : NetworkResponse<T>()
}
