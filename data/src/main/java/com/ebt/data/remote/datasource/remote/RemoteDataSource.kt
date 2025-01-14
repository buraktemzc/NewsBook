package com.ebt.data.remote.datasource.remote

import com.ebt.core.model.Resource
import com.ebt.data.model.response.NewsResponse

interface RemoteDataSource {
    suspend fun getPosts() : Resource<List<NewsResponse>>
}