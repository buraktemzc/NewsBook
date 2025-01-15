package com.ebt.data.local.datastore

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ebt.data.model.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<NewsEntity>)

    @Query("SELECT * FROM news")
    fun getAll(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE rowId= :rowId")
    fun getNewsByRowId(rowId: Long): NewsEntity?

    @Query("SELECT count(*) from news")
    suspend fun getCount(): Long

    @Query("UPDATE news SET title = :title, body = :description, updated = 1 WHERE rowId = :rowId")
    suspend fun updateNews(rowId: Long, title: String, description: String)
}