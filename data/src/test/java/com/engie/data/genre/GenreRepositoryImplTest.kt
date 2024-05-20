package com.engie.data.genre

import com.engie.data.api.MovieApiService
import com.engie.data.common.TestData
import com.engie.data.common.UnitTest
import com.engie.domain.genre.GenreRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GenreRepositoryImplTest : UnitTest() {

    private val api: MovieApiService = mock()
    private lateinit var repository: GenreRepository

    @Before
    fun init() {
        repository = GenreRepositoryImpl(
            api = api,
            dispatcher = dispatcher,
            mapper = mock()
        )
    }

    @Test
    fun `call api when genre is invoked`() {
        runBlocking {
            // when we call genre list
            repository.getGenre(apikey = "")

            // then we verify that the api is called
            verify(api).getGenre(apiKey = "")
        }
    }

    @Test
    fun `return success when movie search succeeds`() {
        runBlocking {
            whenever(api.getGenre(apiKey = ""))
                .thenReturn(Response.success(TestData.createSuccessfulGenreResponse()))

            // when we fetch genres
            val result = repository.getGenre(apikey = "")

            // then we assert that success is returned
            Assert.assertTrue(result.isSuccess())
        }
    }
}