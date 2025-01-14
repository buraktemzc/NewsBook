package com.ebt.domain.model

import com.ebt.base.domain.DomainModel

data class NewsDomainModel(
    val rowId: Long,
    val id: Long,
    val userId: Long,
    val title: String,
    val description: String,
    val updated: Boolean
) : DomainModel
