package com.ebt.domain.usecase

import com.ebt.base.domain.usecase.FlowUseCase
import com.ebt.core.model.IoDispatcher
import com.ebt.domain.model.NewsDomainModel
import com.ebt.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsFromDBUseCase @Inject constructor(
    private val repository: NewsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<NewsDomainModel>>(dispatcher) {

    override fun getExecutable(params: Unit): Flow<List<NewsDomainModel>> =
        repository.getAllPostsFromDB()
}