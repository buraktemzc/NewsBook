package com.ebt.data.remote.datasource.remote

import com.ebt.base.data.getResourceFromResponse
import com.ebt.core.model.Resource
import com.ebt.data.model.response.NewsResponse
import com.ebt.data.remote.api.ApiService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: ApiService) : RemoteDataSource {

    override suspend fun getPosts(): Resource<List<NewsResponse>> =
        api.getPosts().getResourceFromResponse()
}