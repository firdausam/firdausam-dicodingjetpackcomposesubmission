package com.firdausam.dicodingjcomposesub.domain.repository

import com.firdausam.dicodingjcomposesub.domain.model.Anime
import com.firdausam.dicodingjcomposesub.domain.model.DetailAnime
import com.firdausam.dicodingjcomposesub.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getAnimeBySearch(keyword: String = ""): Resource<List<Anime>>
    suspend fun getAnimeById(id: Int): Resource<DetailAnime>
    suspend fun saveFavorite(detailAnime: DetailAnime)
    suspend fun removeFavorite(id: Int)
    fun getFavoritesAnime(): Flow<List<Anime>>
    fun isAnimeFavorite(id: Int): Flow<Boolean>
}