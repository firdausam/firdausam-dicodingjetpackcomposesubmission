package com.firdausam.dicodingjcomposesub.data

import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.data.local.enity.AnimeEntity
import com.firdausam.dicodingjcomposesub.domain.model.Anime
import com.firdausam.dicodingjcomposesub.domain.model.DetailAnime
import com.firdausam.dicodingjcomposesub.ui.component.InfoItem

fun Anime.toEntity() = AnimeEntity(
    id = id,
    title = title,
    image = image,
    type = type,
    episodes = episodes,
    members = members,
    score = score
)

val fakeAnime: Anime = Anime(
    id = 41467,
    title = "Bleach: Sennen Kessen-hen",
    image = "https://cdn.myanimelist.net/images/anime/1764/126627.jpg",
    type = "TV",
    episodes = 13,
    members = 298489,
    score = 9.12
)

val fakeAnimeList: List<Anime>
    get() = ArrayList<Anime>().apply {
        add(fakeAnime)
        (1..10).forEach {
            add(
                fakeAnime.copy(
                    id = (fakeAnime.id + (3 * it)),
                    title = fakeAnime.title + " (${it + 1})"
                )
            )
        }
    }

val fakeDetailAnime: DetailAnime = DetailAnime(
    id = 41467,
    title = "Bleach: Sennen Kessen-hen",
    image = "https://cdn.myanimelist.net/images/anime/1764/126627.jpg",
    type = "TV",
    episodes = 13,
    members = 298489,
    score = 9.12,
    ranked = 1,
    popularity = 697,
    status = "Currently Airing",
    aired = "Oct 11, 2022 to ?",
    premiered = "premiered",
    broadcast = "Tuesdays at 00:00 (JST)",
    producers = "TV Tokyo",
    licensors = "VIZ Media",
    studios = "Pierrot",
    source = "Manga",
    genres = "Action",
    demographics = "Shounen",
    duration = "24 min per ep",
    rating = "R - 17+ (violence & profanity)",
    synopsis = "Substitute Soul Reaper Ichigo Kurosaki spends his days fighting against Hollows, dangerous evil spirits that threaten Karakura Town. Ichigo carries out his quest with his closest allies: Orihime Inoue, his childhood friend with a talent for healing; Yasutora Sado, his high school classmate with superhuman strength; and Uryuu Ishida, Ichigo's Quincy rival.\\n\\nIchigo's vigilante routine is disrupted by the sudden appearance of Asguiaro Ebern, a dangerous Arrancar who heralds the return of Yhwach, an ancient Quincy king. Yhwach seeks to reignite the historic blood feud between Soul Reaper and Quincy, and he sets his sights on erasing both the human world and the Soul Society for good.\\n\\nYhwach launches a two-pronged invasion into both the Soul Society and Hueco Mundo, the home of Hollows and Arrancar. In retaliation, Ichigo and his friends must fight alongside old allies and enemies alike to end Yhwach's campaign of carnage before the world itself comes to an end.\\n\\n[Written by MAL Rewrite]",
    background = "The anime adapts the final manga arc, Sennen Kessen-hen (Thousand-Year Blood War Arc), which spans from the 55th to the 74th volumes.",
)

val fakeEntity get() = fakeAnime.toEntity()

const val InformationTest = "Information Test"
val fakeInfoItem get() = InfoItem(R.string.information, InformationTest)

const val fakeErrorMessage = "error message"