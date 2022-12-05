package com.firdausam.dicodingjcomposesub.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.firdausam.dicodingjcomposesub.data.local.enity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun getFavoritesAnime(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAnime(anime: AnimeEntity)

    @Query("DELETE FROM anime WHERE id = :id")
    suspend fun deleteAnime(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM anime WHERE id = :id)")
    fun isAnimeFavorite(id: Int): Flow<Boolean>

    @Query("DELETE FROM anime")
    suspend fun deleteAllAnime()

}