package com.ebt.domain.repository

import com.ebt.core.model.Resource
import com.ebt.domain.model.NewsDomainModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchPosts(): Resource<Unit>
    suspend fun getNumberOfPosts(): Long
    suspend fun getPostByRowId(rowId: Long): Resource<NewsDomainModel>
    suspend fun updatePost(rowId: Long, title: String, description: String)
    fun getAllPostsFromDB(): Flow<List<NewsDomainModel>>
}