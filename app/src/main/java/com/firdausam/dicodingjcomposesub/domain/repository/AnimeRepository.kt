package com.firdausam.dicodingjcomposesub.domain.repository

import com.firdausam.dicodingjcomposesub.domain.model.Anime
import com.firdausam.dicodingjcomposesub.domain.model.DetailAnime
import com.firdausam.dicodingjcomposesub.domain.util.Resource

interface AnimeRepository {
    suspend fun getAnimeBySearch(keyword: String = ""): Resource<List<Anime>>
    suspend fun getAnimeById(id: Int): Resource<DetailAnime>
}