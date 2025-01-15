package com.ebt.data.local.datastore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsDaoTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: NewsDao
    private lateinit var database: NewsDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.newsDao()
    }

    @After
    fun tearDown() = database.close()

    @Test
    fun given_insert_list_of_news_When_getCount_called_Then_count_must_be_size_of_list() =
        runTest {
            //Given
            val newsEntities = NewsEntityFactory.getMockNewsList()
            dao.insertAll(newsEntities)

            //When
            val count = dao.getCount()

            //Then
            assertThat(count).isEqualTo(newsEntities.size)
        }

    @Test
    fun given_insert_news_When_updateNews_called_Then_updated_is_true() = runTest {
        //Given
        val defaultNews = NewsEntityFactory.getMockNewsEntity(NewsEntityFactory.RowId.FIRST)
        val rowId = defaultNews.rowId

        //When
        dao.insertAll(listOf(defaultNews))
        dao.updateNews(rowId, title = "asd", description = "zxc")
        val changedNews = dao.getNewsByRowId(rowId)

        //Then
        assertThat(changedNews?.updated).isTrue()
    }
}