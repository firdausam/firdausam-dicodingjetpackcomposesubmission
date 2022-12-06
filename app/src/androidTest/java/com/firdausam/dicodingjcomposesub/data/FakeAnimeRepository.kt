package com.firdausam.dicodingjcomposesub.data

import com.firdausam.dicodingjcomposesub.domain.model.Anime
import com.firdausam.dicodingjcomposesub.domain.model.DetailAnime
import com.firdausam.dicodingjcomposesub.domain.repository.AnimeRepository
import com.firdausam.dicodingjcomposesub.domain.util.Resource
import com.firdausam.dicodingjcomposesub.util.TagEmpty
import com.firdausam.dicodingjcomposesub.util.TagError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class FakeAnimeWithDataRepository : AnimeRepository {

    private val favoriteFlow = MutableStateFlow(false)

    override suspend fun getAnimeBySearch(keyword: String): Resource<List<Anime>> {
        if (keyword == TagEmpty) {
            return Resource.Success(listOf())
        } else if (keyword == TagError) {
            return Resource.Error(fakeErrorMessage)
        }
        return Resource.Success(fakeAnimeList)
    }

    override suspend fun getAnimeById(id: Int): Resource<DetailAnime> {
        if (id != fakeDetailAnime.id) {
            return Resource.Error(fakeErrorMessage)
        }
        return Resource.Success(fakeDetailAnime)
    }

    override suspend fun saveFavorite(detailAnime: DetailAnime) {
        favoriteFlow.value = detailAnime.id == fakeDetailAnime.id
    }

    override suspend fun removeFavorite(id: Int) {
        favoriteFlow.value = false
    }

    override fun getFavoritesAnime(): Flow<List<Anime>> {
        return flow { emit(fakeAnimeList) }
    }

    override fun isAnimeFavorite(id: Int): Flow<Boolean> {
        return favoriteFlow
    }
}

class FakeAnimeWithoutDataRepository : AnimeRepository {

    override suspend fun getAnimeBySearch(keyword: String): Resource<List<Anime>> {
        return Resource.Success(listOf())
    }

    override suspend fun getAnimeById(id: Int): Resource<DetailAnime> {
        return Resource.Error(fakeErrorMessage)
    }

    override suspend fun saveFavorite(detailAnime: DetailAnime) {}

    override suspend fun removeFavorite(id: Int) {}

    override fun getFavoritesAnime(): Flow<List<Anime>> {
        return flow { emit(listOf()) }
    }

    override fun isAnimeFavorite(id: Int): Flow<Boolean> {
        return flow { emit(false) }
    }
}