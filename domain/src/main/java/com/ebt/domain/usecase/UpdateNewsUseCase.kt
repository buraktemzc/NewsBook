package com.ebt.domain.usecase

import com.ebt.base.domain.usecase.UseCase
import com.ebt.core.model.IoDispatcher
import com.ebt.core.model.Resource
import com.ebt.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<UpdateNewsUseCase.UpdateParam, Unit>(dispatcher) {

    override suspend fun getExecutable(params: UpdateParam): Resource<Unit> {
        repository.updatePost(
            rowId = params.rowId,
            title = params.title,
            description = params.description
        )
        return Resource.Success(Unit)
    }

    data class UpdateParam(
        val rowId: Long,
        val title: String,
        val description: String
    )
}