package com.firdausam.dicodingjcomposesub.data.local.enity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnimeEntity(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: Int,
    @field:ColumnInfo(name = "title")
    val title: String,
    @field:ColumnInfo(name = "image")
    val image: String,
    @field:ColumnInfo(name = "type")
    val type: String,
    @field:ColumnInfo(name = "episodes")
    val episodes: Int,
    @field:ColumnInfo(name = "members")
    val members: Int,
    @field:ColumnInfo(name = "score")
    val score: Double,
)