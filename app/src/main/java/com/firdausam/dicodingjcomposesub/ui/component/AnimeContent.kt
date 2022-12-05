package com.firdausam.dicodingjcomposesub.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.firdausam.dicodingjcomposesub.domain.model.Anime
import com.firdausam.dicodingjcomposesub.util.TagListItem

@Composable
fun AnimeContent(
    anime: List<Anime>,
    navigateToDetail: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier.testTag(TagListItem),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(anime, key = { it.id }) { item ->
            with(item) {
                AnimeItem(
                    title = title,
                    image = image,
                    type = type,
                    episodeCount = episodes,
                    memberCount = members,
                    score = score,
                    modifier = Modifier.clickable {
                        navigateToDetail(item.id)
                    }
                )
            }
        }
    }
}