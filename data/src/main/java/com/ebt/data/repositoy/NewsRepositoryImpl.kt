package com.ebt.data.repositoy

import com.ebt.core.model.Resource
import com.ebt.data.mapper.NewsDomainMapper
import com.ebt.data.mapper.NewsEntityListMapper
import com.ebt.data.mapper.NewsListDomainMapper
import com.ebt.data.remote.datasource.local.LocalDataSource
import com.ebt.data.remote.datasource.remote.RemoteDataSource
import com.ebt.domain.model.NewsDomainModel
import com.ebt.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val newsEntityListMapper: NewsEntityListMapper,
    private val newsListDomainMapper: NewsListDomainMapper,
    private val newsDomainMapper: NewsDomainMapper
) : NewsRepository {
    override suspend fun fetchPosts(): Resource<Unit> {
        return when (val response = remoteDataSource.getPosts()) {
            is Resource.Success -> {
                response.value.let { newsList ->
                    val entityList = newsEntityListMapper.mapToEntityList(newsList)
                    localDataSource.insertAllPosts(entityList)
                }
                Resource.Success(Unit)
            }

            is Resource.Failure -> Resource.Failure(response.error)
        }
    }

    override suspend fun getNumberOfPosts(): Long = localDataSource.getNumberOfNews()

    override suspend fun getPostByRowId(rowId: Long): Resource<NewsDomainModel> {
        val entity = localDataSource.getNewsByRowId(rowId)

        val domainModel: NewsDomainModel = if (entity == null) {
            NewsDomainModel(
                rowId = 0L,
                id = 0L,
                userId = 0L,
                title = "",
                description = "",
                imageURL = "",
                updated = false
            )
        } else {
            newsDomainMapper.mapToDomainModel(entity)
        }

        return Resource.Success(domainModel)
    }

    override suspend fun updatePost(rowId: Long, title: String, description: String) =
        localDataSource.updateNews(rowId = rowId, title = title, description = description)

    override fun getAllPostsFromDB(): Flow<List<NewsDomainModel>> {
        return localDataSource.readAllNews().map { newsList ->
            newsListDomainMapper.mapToDomainModel(newsList)
        }
    }
}