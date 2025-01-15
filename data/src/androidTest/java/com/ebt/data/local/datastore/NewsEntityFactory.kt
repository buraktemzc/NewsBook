package com.ebt.data.local.datastore

import com.ebt.data.model.entity.NewsEntity

object NewsEntityFactory {
    object RowId{
        const val FIRST = 1L
        const val SECOND = 2L
        const val THIRD = 3L
    }

    fun getMockNewsEntity(rowId: Long) = NewsEntity(
        rowId = rowId,
        userId = 1,
        id = 2,
        title = "Asdas",
        body = "asa asasas asas asa",
        imageURL = "asasas",
        updated = false
    )

    fun getMockNewsList() : List<NewsEntity> = listOf(
        getMockNewsEntity(RowId.FIRST),
        getMockNewsEntity(RowId.SECOND),
        getMockNewsEntity(RowId.THIRD)
    )
}