package com.ebt.network.manager.retrofit

import com.ebt.network.model.Error
import com.ebt.network.model.NetworkResponse
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLPeerUnverifiedException

internal class NetworkResponseCall<T : Any>(
    private val delegate: Call<T>,
    private val errorConverter: Converter<ResponseBody, Error.ApiError>
) : Call<NetworkResponse<T>> {
    override fun clone(): Call<NetworkResponse<T>> =
        NetworkResponseCall(delegate.clone(), errorConverter)

    override fun execute(): Response<NetworkResponse<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    override fun enqueue(callback: Callback<NetworkResponse<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(NetworkResponse.Success(body))
                    )
                } else {
                    val errorBody = response.errorBody()
                    if (errorBody == null) {
                        notifyCallBackWithError(
                            callback = callback,
                            error = Error.UnKnownError
                        )
                    } else {
                        val apiError = parseApiError(errorBody)
                        if (apiError?.message == null) {
                            notifyCallBackWithError(
                                callback = callback,
                                error = Error.UnKnownError
                            )
                        } else {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.Failure(
                                        Error.ApiError(
                                            apiError.code,
                                            apiError.message
                                        )
                                    )
                                )
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                notifyCallBackWithError(
                    callback = callback,
                    error = getErrorFromException(t)
                )
            }
        })
    }

    private fun parseApiError(errorBody: ResponseBody?): Error.ApiError? {
        return when {
            errorBody == null -> null
            errorBody.contentLength() == 0L -> null
            else -> try {
                errorConverter.convert(errorBody)
            } catch (ex: Exception) {
                null
            }
        }
    }

    private fun notifyCallBackWithError(callback: Callback<NetworkResponse<T>>, error: Error) {
        callback.onResponse(
            this@NetworkResponseCall,
            Response.success(NetworkResponse.Failure(error))
        )
    }

    private fun getErrorFromException(t: Throwable): Error {
        return when (t) {
            is SSLPeerUnverifiedException -> Error.SSLException
            is UnknownHostException -> Error.ConnectionError
            is SocketTimeoutException -> Error.TimeOutException(t.localizedMessage)
            else -> Error.UnKnownError
        }
    }
}