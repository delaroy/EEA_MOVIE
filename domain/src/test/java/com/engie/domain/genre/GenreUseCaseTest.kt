package com.engie.domain.genre

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GenreUseCaseTest {

    private lateinit var genreUseCase: GetGenreUseCase
    private val genreRepository: GenreRepository = mock()

    @Before
    fun init() {
        genreUseCase = GetGenreUseCase(genreRepository)
    }

    @Test
    fun `call genre repository`() {
        runBlocking {
            // when we invoke the use case
            val apiKey = ""
            genreUseCase(apiKey)

            // then we verify that the repository is called
            verify(genreRepository).getGenre(apiKey)
        }
    }
}