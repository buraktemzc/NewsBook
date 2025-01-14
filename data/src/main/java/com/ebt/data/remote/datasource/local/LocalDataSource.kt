package com.ebt.data.remote.datasource.local

import com.ebt.data.model.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertAllPosts(list: List<NewsEntity>)

    suspend fun getNumberOfNews() : Long

    fun readAllNews(): Flow<List<NewsEntity>>
}