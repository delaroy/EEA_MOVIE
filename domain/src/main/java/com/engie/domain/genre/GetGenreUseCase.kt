package com.engie.domain.genre

import com.engie.domain.model.GenreData
import com.engie.domain.util.Resource
import com.engie.domain.util.SuspendUseCase

class GetGenreUseCase(private val genreRepository: GenreRepository) :
    SuspendUseCase<String, Resource<List<GenreData>>>() {

    override suspend fun invoke(param: String): Resource<List<GenreData>> =
        genreRepository.getGenre(apikey = param)
}