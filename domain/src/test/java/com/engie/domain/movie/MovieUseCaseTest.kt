package com.engie.domain.movie

import com.engie.domain.model.MovieRequestParam
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieUseCaseTest {

    private lateinit var moviesUseCase: GetMoviesUseCase
    private val movieRepository: MovieRepository = mock()

    @Before
    fun init() {
        moviesUseCase = GetMoviesUseCase(movieRepository)
    }

    @Test
    fun `call movie repository`() {
        runBlocking {
            // when we invoke the use case
            val movieRequestParam = MovieRequestParam(apiKey = "", query = "")
            moviesUseCase(movieRequestParam)

            // then we verify that the repository is called
            verify(movieRepository).getMovies(movieRequestParam.apiKey, movieRequestParam.query)
        }
    }
}