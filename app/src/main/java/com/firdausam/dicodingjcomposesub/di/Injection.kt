package com.firdausam.dicodingjcomposesub.di

import android.content.Context
import com.firdausam.dicodingjcomposesub.data.AnimeDataRepository
import com.firdausam.dicodingjcomposesub.domain.repository.AnimeRepository
import com.firdausam.dicodingjcomposesub.data.local.room.AnimeDatabase
import com.firdausam.dicodingjcomposesub.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): AnimeRepository {
        val apiService = ApiConfig.getApiService()
        val database = AnimeDatabase.getInstance(context)
        val dao = database.animeDao()
        return AnimeDataRepository.getInstance(apiService, dao)
    }
}