package com.ebt.domain.usecase

import com.ebt.base.domain.usecase.UseCase
import com.ebt.core.model.IoDispatcher
import com.ebt.core.model.Resource
import com.ebt.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FetchNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Unit, Unit>(dispatcher) {

    override suspend fun getExecutable(params: Unit): Resource<Unit> {
        return when (val response = repository.fetchPosts()) {
            is Resource.Success -> Resource.Success(response.value)
            is Resource.Failure -> Resource.Failure(response.error)
        }
    }
}