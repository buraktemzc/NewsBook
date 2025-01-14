package com.ebt.base.ui

import com.ebt.base.domain.DomainModel

interface UIMapper<D : DomainModel, U : UIModel> {
    fun mapToUIModel(domainModel: D): U
}