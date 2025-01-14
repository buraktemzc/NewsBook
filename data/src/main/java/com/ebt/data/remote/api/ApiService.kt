package com.ebt.data.remote.api

import com.ebt.data.model.response.NewsResponse
import com.ebt.network.model.NetworkResponse
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): NetworkResponse<List<NewsResponse>>
}