package com.firdausam.dicodingjcomposesub.data

import android.util.Log
import com.firdausam.dicodingjcomposesub.data.converter.toAnime
import com.firdausam.dicodingjcomposesub.data.converter.toDetailAnime
import com.firdausam.dicodingjcomposesub.data.converter.toEntity
import com.firdausam.dicodingjcomposesub.data.local.room.AnimeDao
import com.firdausam.dicodingjcomposesub.data.remote.retrofit.ApiService
import com.firdausam.dicodingjcomposesub.domain.model.Anime
import com.firdausam.dicodingjcomposesub.domain.model.DetailAnime
import com.firdausam.dicodingjcomposesub.domain.repository.AnimeRepository
import com.firdausam.dicodingjcomposesub.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeDataRepository constructor(
    private val apiService: ApiService,
    private val animeDao: AnimeDao
) : AnimeRepository {

    companion object {
        private val TAG = AnimeDataRepository::class.java.simpleName

        @Volatile
        private var instance: AnimeRepository? = null
        fun getInstance(
            apiService: ApiService,
            animeDao: AnimeDao,
        ): AnimeRepository = instance ?: synchronized(this) {
            instance ?: AnimeDataRepository(apiService, animeDao)
        }.also { instance = it }

    }

    override suspend fun getAnimeBySearch(keyword: String): Resource<List<Anime>> {
        return try {
            val response = apiService.getAnime(keyword)
            val data = response.data
            val anime = data.map {
                it.toAnime()
            }
            Resource.Success(anime)
        } catch (e: Exception) {
            Log.e(TAG, "getAnime: ", e)
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getAnimeById(id: Int): Resource<DetailAnime> {
        return try {
            val response = apiService.getAnimeById(id)
            Resource.Success(response.data.toDetailAnime())
        } catch (e: Exception) {
            Log.e(TAG, "getAnime: ", e)
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun saveFavorite(detailAnime: DetailAnime) {
        animeDao.saveAnime(detailAnime.toEntity())
    }

    override suspend fun removeFavorite(id: Int) {
        animeDao.deleteAnime(id)
    }

    override fun getFavoritesAnime(): Flow<List<Anime>> {
        return animeDao.getFavoritesAnime().map { data ->
            data.map { it.toAnime() }
        }
    }

    override fun isAnimeFavorite(id: Int): Flow<Boolean> {
        return animeDao.isAnimeFavorite(id)
    }
}