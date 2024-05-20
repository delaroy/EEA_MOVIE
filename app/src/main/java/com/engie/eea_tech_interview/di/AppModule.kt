package com.engie.eea_tech_interview.di

import com.engie.eea_tech_interview.BuildConfig
import com.engie.eea_tech_interview.ui.genre.GenreViewModel
import com.engie.eea_tech_interview.ui.movie.MovieFragmentViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    // Base Url
    single(named("base_url")) { BuildConfig.BASE_URL }

    // Gson
    factory { Gson() }
}

val viewModelModule = module {

    viewModel {
        MovieFragmentViewModel(
            getMoviesUseCase = get()
        )
    }

    viewModel {
        GenreViewModel(
            getGenreUseCase = get()
        )
    }

}

val appModule = arrayListOf(
    commonModule,
    viewModelModule
)