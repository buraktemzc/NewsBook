package com.ebt.data.local.datastore

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ebt.data.model.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun newsDao() : NewsDao

    companion object {
        const val DATABASE_NAME = "news_db"
    }
}