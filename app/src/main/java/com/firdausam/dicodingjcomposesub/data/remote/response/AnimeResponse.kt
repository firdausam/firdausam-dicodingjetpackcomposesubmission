package com.firdausam.dicodingjcomposesub.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("mal_id")
    val malId: Int,
    val url: String,
    val images: ImageResponse,
    val title: String,
    val type: String,
    val source: String,
    val episodes: Int,
    val status: String,
    val airing: Boolean,
    val aired: AiredResponse,
    val duration: String,
    val rating: String,
    val score: Double,
    @SerializedName("scored_by")
    val scoredBy: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val synopsis: String,
    val background: String? = null,
    val season: String,
    val year: Int,
    val broadcast: BroadcastResponse? = null,
    val trailer: TrailerResponse? = null,
    val producers: List<CommonComponentResponse>? = null,
    val licensors: List<CommonComponentResponse>? = null,
    val studios: List<CommonComponentResponse>? = null,
    val genres: List<CommonComponentResponse>? = null,
    val themes: List<CommonComponentResponse>? = null,
    val demographics: List<CommonComponentResponse>? = null
)

data class ImageResponse(
    val jpg: ImageItemResponse
)

data class ImageItemResponse(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("large_image_url")
    val largeImageUrl: String,
    @SerializedName("small_image_url")
    val smallImageUrl: String
)

data class TrailerResponse(
    val url: String,
)

data class BroadcastResponse(
    val string: String
)

data class AiredResponse(
    val from: String,
    val to: String,
    val string: String
)

data class CommonComponentResponse(
    val name: String
)