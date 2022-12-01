package com.firdausam.dicodingjcomposesub.domain.model

data class Anime(
    val id: Int,
    val title: String,
    val image: String,
    val type: String,
    val episodes: Int,
    val members: Int,
    val score: Double
)