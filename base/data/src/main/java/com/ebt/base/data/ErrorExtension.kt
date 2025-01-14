package com.ebt.base.data

import com.ebt.core.model.NewsError
import com.ebt.network.model.Error

fun Error.toNewsError(): NewsError = when (this) {
    is Error.ApiError -> NewsError.ApiError(code, message)
    is Error.TimeOutException -> NewsError.TimeOutException(message)
    is Error.ConnectionError -> NewsError.ConnectionError
    else -> NewsError.UnKnownError
}