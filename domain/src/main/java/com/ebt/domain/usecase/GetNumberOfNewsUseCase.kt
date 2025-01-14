package com.ebt.domain.usecase

import com.ebt.base.domain.usecase.UseCase
import com.ebt.core.model.IoDispatcher
import com.ebt.core.model.Resource
import com.ebt.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetNumberOfNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Unit, Long>(dispatcher) {

    override suspend fun getExecutable(params: Unit): Resource<Long> {
        return Resource.Success(repository.getNumberOfPosts())
    }
}