package com.engie.eea_tech_interview.di

import com.engie.domain.genre.GetGenreUseCase
import com.engie.domain.movie.GetMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetMoviesUseCase(movieRepository = get()) }

    factory { GetGenreUseCase(genreRepository = get()) }
}
val domainModule = arrayListOf(useCaseModule)