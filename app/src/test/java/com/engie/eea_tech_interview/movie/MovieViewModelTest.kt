package com.engie.eea_tech_interview.movie

import android.app.Application
import com.engie.domain.model.MovieData
import com.engie.domain.movie.GetMoviesUseCase
import com.engie.eea_tech_interview.common.UnitTest
import com.engie.eea_tech_interview.ui.movie.MovieFragmentViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieViewModelTest : UnitTest() {
    private val moviesUseCase: GetMoviesUseCase = GetMoviesUseCase(FakeMovieRepository())

    private lateinit var viewModel: MovieFragmentViewModel

    @Mock
    private lateinit var context: Application


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(context.applicationContext).thenReturn(context)

        viewModel = MovieFragmentViewModel(
            getMoviesUseCase = moviesUseCase
        )
    }

    @Test
    fun `Get movies list returned` (): Unit = runBlocking{

        val response = mutableListOf<MovieData>()
        with(viewModel) {
            fetchMovies(search = "James")
        }
        viewModel.movies.observeForever {
            it?.getContentIfNotHandled()?.let {
                response.addAll(it.data!!)
            }

            Assert.assertTrue(
                response.size == 2)

        }
    }
}