package com.engie.eea_tech_interview.genre

import android.app.Application
import com.engie.domain.genre.GetGenreUseCase
import com.engie.domain.model.GenreData
import com.engie.eea_tech_interview.common.UnitTest
import com.engie.eea_tech_interview.ui.genre.GenreViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GenreViewModelTest : UnitTest() {
    private val genreUseCase: GetGenreUseCase = GetGenreUseCase(FakeGenreRepository())

    private lateinit var viewModel: GenreViewModel

    @Mock
    private lateinit var context: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(context.applicationContext).thenReturn(context)

        viewModel = GenreViewModel(
            getGenreUseCase = genreUseCase
        )
    }

    @Test
    fun `Get genre list returned` (): Unit = runBlocking{

        val response = mutableListOf<GenreData>()
        with(viewModel) {
            fetchGenre()
        }
        viewModel.genre.observeForever {
            it?.getContentIfNotHandled()?.let {
                response.addAll(it.data!!)
            }

            Assert.assertTrue(
                response.size == 3)

        }
    }
}