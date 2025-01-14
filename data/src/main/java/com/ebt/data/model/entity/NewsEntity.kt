package com.ebt.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ebt.base.data.model.EntityModel

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val rowId: Long = 0L,
    val userId: Long?,
    val id: Long?,
    val title: String?,
    val body: String?,
    val updated: Boolean = false
) : EntityModel