package com.engie.data.movie

import com.engie.data.api.MovieApiService
import com.engie.data.common.TestData
import com.engie.data.common.UnitTest
import com.engie.domain.model.MovieRequestParam
import com.engie.domain.movie.MovieRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MovieRepositoryImplTest : UnitTest() {

    private val api: MovieApiService = mock()

    private val movieRequestParam = MovieRequestParam(apiKey = "abc", query = "bcd")
    private lateinit var repository: MovieRepository

    @Before
    fun init() {
        repository = MovieRepositoryImpl(
            api = api,
            dispatcher = dispatcher,
            mapper = mock()
        )
    }

    @Test
    fun `call api when movie is invoked`() {
        runBlocking {
            // when we call movie search
            repository.getMovies(apiKey = movieRequestParam.apiKey, query = movieRequestParam.query)

            // then we verify that the api is called
            verify(api).getMovies(apiKey = movieRequestParam.apiKey, query = movieRequestParam.query)
        }
    }

    @Test
    fun `return success when movie search succeeds`() {
        runBlocking {
            whenever(api.getMovies(apiKey = movieRequestParam.apiKey, query = movieRequestParam.query))
                .thenReturn(Response.success(TestData.createSuccessfulMovieResponse()))

            // when we fetch movies
            val result = repository.getMovies(apiKey = movieRequestParam.apiKey, query = movieRequestParam.query)

            // then we assert that success is returned
            Assert.assertTrue(result.isSuccess())
        }
    }
}
