package com.firdausam.dicodingjcomposesub.domain.model

data class DetailAnime(
    val id: Int,
    val title: String,
    val image: String,
    val type: String,
    val episodes: Int,
    val members: Int,
    val score: Double,
    val ranked: Int,
    val popularity: Int,
    val status: String,
    val aired: String,
    val premiered: String,
    val broadcast: String,
    val producers: String,
    val licensors: String,
    val studios: String,
    val source: String,
    val genres: String,
    val demographics: String,
    val duration: String,
    val rating: String,
    val synopsis: String,
    val background: String?
)