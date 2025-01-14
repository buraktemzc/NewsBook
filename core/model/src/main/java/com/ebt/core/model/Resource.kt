package com.ebt.core.model

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(val error: NewsError) : Resource<Nothing>()
}