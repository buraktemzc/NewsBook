package com.ebt.data.di.database

import android.content.Context
import androidx.room.Room
import com.ebt.data.local.datastore.NewsDao
import com.ebt.data.local.datastore.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(
            context, NewsDatabase::class.java, NewsDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao()
}