package com.ebt.data.remote.datasource.local

import com.ebt.data.model.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertAllPosts(list: List<NewsEntity>)

    suspend fun getNumberOfNews() : Long

    suspend fun getNewsByRowId(rowId: Long) : NewsEntity?

    suspend fun updateNews(rowId: Long, title: String, description: String)

    suspend fun removeNews(rowId: Long)

    fun readAllNews(): Flow<List<NewsEntity>>
}