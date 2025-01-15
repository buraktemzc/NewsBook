package com.ebt.data.remote.datasource.local

import com.ebt.data.local.datastore.NewsDao
import com.ebt.data.model.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: NewsDao) : LocalDataSource {

    override suspend fun insertAllPosts(list: List<NewsEntity>) = dao.insertAll(list)
    override suspend fun getNumberOfNews(): Long = dao.getCount()
    override suspend fun getNewsByRowId(rowId: Long): NewsEntity? =
        dao.getNewsByRowId(rowId = rowId)

    override suspend fun updateNews(rowId: Long, title: String, description: String) =
        dao.updateNews(rowId = rowId, title = title, description = description)

    override suspend fun removeNews(rowId: Long) = dao.removeNews(rowId)

    override fun readAllNews(): Flow<List<NewsEntity>> = dao.getAll()
}