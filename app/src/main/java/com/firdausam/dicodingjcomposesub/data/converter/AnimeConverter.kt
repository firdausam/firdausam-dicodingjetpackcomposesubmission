package com.firdausam.dicodingjcomposesub.data.converter

import com.firdausam.dicodingjcomposesub.data.local.enity.AnimeEntity
import com.firdausam.dicodingjcomposesub.data.remote.response.AnimeResponse
import com.firdausam.dicodingjcomposesub.data.remote.response.CommonComponentResponse
import com.firdausam.dicodingjcomposesub.domain.model.Anime
import com.firdausam.dicodingjcomposesub.domain.model.DetailAnime

fun AnimeResponse.toAnime(): Anime {
    return Anime(
        id = malId,
        title = title,
        image = images.jpg.imageUrl,
        type = type,
        episodes = episodes,
        members = members,
        score = score
    )
}

fun AnimeResponse.toDetailAnime(): DetailAnime {
    fun List<CommonComponentResponse>?.join(): String {
        return this?.joinToString(", ") { it.name } ?: ""
    }
    return DetailAnime(
        id = malId,
        title = title,
        image = images.jpg.imageUrl,
        type = type,
        episodes = episodes,
        members = members,
        score = score,
        ranked = rank,
        popularity = popularity,
        status = status,
        aired = aired.string,
        premiered = "$season $year",
        broadcast = broadcast?.string ?: "-",
        producers = producers.join(),
        licensors = licensors.join(),
        studios = studios.join(),
        source = source,
        genres = genres.join(),
        demographics = demographics.join(),
        duration = duration,
        rating = rating,
        synopsis = synopsis,
        background = background
    )
}

fun Anime.toEntity(): AnimeEntity {
    return AnimeEntity(
        id = id,
        title = title,
        image = image,
        type = type,
        episodes = episodes,
        members = members,
        score = score
    )
}

fun AnimeEntity.toAnime(): Anime {
    return Anime(
        id = id,
        title = title,
        image = image,
        type = type,
        episodes = episodes,
        members = members,
        score = score
    )
}