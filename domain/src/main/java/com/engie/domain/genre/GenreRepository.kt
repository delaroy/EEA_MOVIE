package com.engie.domain.genre

import com.engie.domain.model.GenreData
import com.engie.domain.util.Resource

interface GenreRepository {

    suspend fun getGenre(apikey: String): Resource<List<GenreData>>
}