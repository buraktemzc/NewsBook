package com.ebt.domain.repository

import com.ebt.core.model.Resource
import com.ebt.domain.model.NewsDomainModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchPosts(): Resource<Unit>
    fun getAllPostsFromDB(): Flow<List<NewsDomainModel>>
}