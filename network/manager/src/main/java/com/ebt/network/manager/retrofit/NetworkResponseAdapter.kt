package com.ebt.network.manager.retrofit

import com.ebt.network.model.Error
import com.ebt.network.model.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResponseAdapter<T : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, Error.ApiError>
) : CallAdapter<T, Call<NetworkResponse<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<NetworkResponse<T>> =
        NetworkResponseCall(call, errorBodyConverter)
}