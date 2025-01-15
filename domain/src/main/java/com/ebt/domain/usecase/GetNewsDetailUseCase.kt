package com.ebt.domain.usecase

import com.ebt.base.domain.usecase.UseCase
import com.ebt.core.model.IoDispatcher
import com.ebt.core.model.Resource
import com.ebt.domain.model.NewsDomainModel
import com.ebt.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val repository: NewsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Long, NewsDomainModel>(dispatcher) {

    override suspend fun getExecutable(params: Long): Resource<NewsDomainModel> {
        return when (val response = repository.getPostByRowId(rowId = params)) {
            is Resource.Success -> Resource.Success(response.value)
            is Resource.Failure -> Resource.Failure(response.error)
        }
    }
}