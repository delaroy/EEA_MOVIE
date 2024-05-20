package com.engie.eea_tech_interview.genre

import com.engie.domain.genre.GenreRepository
import com.engie.domain.model.GenreData
import com.engie.domain.util.Resource

class FakeGenreRepository : GenreRepository {
    private lateinit var genreList: List<GenreData>

    override suspend fun getGenre(apikey: String): Resource<List<GenreData>> {
        return Resource.success(mockData())
    }


    private fun mockData(): List<GenreData> {
        this.genreList = emptyList()
        val mockList: MutableList<GenreData> = mutableListOf()
        mockList.add(
            GenreData(
                1,
                "Comedy"
            )
        )

        mockList.add(
            GenreData(
                2,
                "Documentary"
            )
        )

        mockList.add(
            GenreData(
                3,
                "Comedy"
            )
        )

        genreList = mockList.toList()
        return genreList
    }

}