package com.ebt.base.data

import com.ebt.core.model.Resource
import com.ebt.network.model.NetworkResponse

fun <T> NetworkResponse<T>.getResourceFromResponse(): Resource<T> =
    when (this) {
        is NetworkResponse.Success -> Resource.Success(body)
        is NetworkResponse.Failure -> Resource.Failure(error.toNewsError())
    }