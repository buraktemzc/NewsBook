package com.ebt.core.model

import java.io.IOException

sealed class NewsError : IOException() {
    class ApiError(var code: String, override var message: String) : NewsError()
    class TimeOutException(override var message: String?) : NewsError()
    data object UnKnownError : NewsError()
    data object ConnectionError : NewsError()
}