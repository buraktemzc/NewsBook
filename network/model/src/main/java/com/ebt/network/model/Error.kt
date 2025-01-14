package com.ebt.network.model

import java.io.IOException

sealed class Error : IOException() {
    class ApiError(var code: String, override var message: String) : Error()
    class TimeOutException(override var message: String?) : Error()
    data object SSLException : Error()
    data object UnKnownError : Error()
    data object ConnectionError : Error()
}
