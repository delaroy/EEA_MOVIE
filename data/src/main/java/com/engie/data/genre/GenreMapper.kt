package com.engie.data.genre

import com.engie.data.model.Genre
import com.engie.data.util.Mapper
import com.engie.domain.model.GenreData

class GenreMapper :
    Mapper<Genre, GenreData> {
    override fun to(domain: GenreData): Genre =
        domain.run {
            Genre(
                id = id,
                name = name
            )
        }

    override fun from(entity: Genre): GenreData =
        entity.run {
            GenreData(
                id = id,
                name = name
            )
        }

}