package com.firdausam.dicodingjcomposesub.ui.screen.detail

import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.domain.model.DetailAnime
import com.firdausam.dicodingjcomposesub.ui.component.InfoItem

fun DetailAnime.toInfo(): List<InfoItem> {
    return listOf(
        InfoItem(R.string.type, type),
        InfoItem(R.string.episodes, episodes.toString()),
        InfoItem(R.string.status, episodes.toString()),
        InfoItem(R.string.aired, aired),
        InfoItem(R.string.premiered, premiered),
        InfoItem(R.string.broadcast, broadcast),
        InfoItem(R.string.producers, producers),
        InfoItem(R.string.licensors, licensors),
        InfoItem(R.string.studios, studios),
        InfoItem(R.string.source, source),
        InfoItem(R.string.genres, genres),
        InfoItem(R.string.genres, genres),
        InfoItem(R.string.demographic, demographics),
        InfoItem(R.string.duration, duration),
        InfoItem(R.string.rating, rating)
    )
}

fun DetailAnime.toDescriptions(): List<InfoItem> {
    return ArrayList<InfoItem>().apply {
        add(InfoItem(R.string.synopsis, synopsis))
        if (background != null) {
            add(InfoItem(R.string.background, background))
        }
    }
}