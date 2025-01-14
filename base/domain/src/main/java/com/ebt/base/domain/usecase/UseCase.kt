package com.ebt.base.domain.usecase

import com.ebt.core.model.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, Type> constructor(
    private val dispatcher: CoroutineDispatcher
) {
    abstract suspend fun getExecutable(params: Params): Resource<Type>

    suspend operator fun invoke(params: Params): Resource<Type> =
        withContext(dispatcher) { getExecutable(params) }
}