package com.ebt.base.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, Type> constructor(
    private val dispatcher: CoroutineDispatcher
) {
    abstract fun getExecutable(params: Params): Flow<Type>

    operator fun invoke(params: Params): Flow<Type> = getExecutable(params).flowOn(dispatcher)
}