package com.firdausam.dicodingjcomposesub.data.remote.retrofit

import com.firdausam.dicodingjcomposesub.data.remote.response.AnimeResponse
import com.firdausam.dicodingjcomposesub.data.remote.response.BaseResponse
import com.firdausam.dicodingjcomposesub.data.remote.response.PagingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("anime")
    suspend fun getAnime(
        @Query("q") q: String,
        @Query("status") status: String = "status",
        @Query("order_by") orderBy: String = "score",
        @Query("sort") sort: String = "desc"
    ): PagingResponse<AnimeResponse>

    @GET("anime/{malId}")
    suspend fun getAnimeById(@Path("malId") malId: Int): BaseResponse<AnimeResponse>
}