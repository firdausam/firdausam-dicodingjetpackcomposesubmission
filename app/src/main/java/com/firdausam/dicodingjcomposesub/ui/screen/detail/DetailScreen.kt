package com.firdausam.dicodingjcomposesub.ui.screen.detail

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.ui.ViewModelFactory
import com.firdausam.dicodingjcomposesub.ui.component.*
import com.firdausam.dicodingjcomposesub.ui.screen.common.BaseScreen
import com.firdausam.dicodingjcomposesub.ui.theme.BlackOpacity
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.ui.theme.Shapes
import com.firdausam.dicodingjcomposesub.util.MySnackbar
import com.firdausam.dicodingjcomposesub.util.TagDetail

@Composable
fun DetailScreen(
    id: Int,
    onBackClick: () -> Unit,
    onToFavorite: () -> Unit,
    mySnackbar: MySnackbar,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context),
        extras = MutableCreationExtras().apply { set(ViewModelFactory.KeyId, id) }
    )
) {
    BaseScreen(
        state = viewModel.state.collectAsState().value,
        modifier = modifier
    ) { result ->
        val isFavorite = viewModel.isFavorite.collectAsState().value
        with(result) {
            Column(modifier = Modifier.testTag(TagDetail)) {
                DetailTopBar(
                    title = title,
                    isFavorite = isFavorite,
                    onClickFavorite = {
                        viewModel.changeFavorite(result)
                    },
                    onBackClick = onBackClick,
                    onFavorite = {
                        DisposableEffect(isFavorite) {
                            if (!viewModel.initFavorite) {
                                mySnackbar.showSnackBar(
                                    message = context.resources.getString(
                                        R.string.save_to_favorite,
                                        title
                                    ),
                                    actionLabel = context.resources.getString(R.string.to_favorite),
                                ) { snackBarResult ->
                                    if (snackBarResult == SnackbarResult.ActionPerformed) {
                                        onToFavorite()
                                    }
                                }
                            }
                            onDispose {
                                mySnackbar.dismiss()
                            }
                        }
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                AnimeDetailContent(
                    image = image,
                    score = score,
                    ranked = ranked,
                    popularity = popularity,
                    members = members,
                    info = toInfo(),
                    descriptions = toDescriptions(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                )
            }
        }
    }
}

@Composable
fun DetailTopBar(
    title: String,
    onClickFavorite: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onFavorite: @Composable () -> Unit = {},
    isFavorite: Boolean = false
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (backIcon, titleText, favoriteIcon) = createRefs()

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(8.dp)
                .clickable { onBackClick() }
                .constrainAs(backIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = 8.dp)
                }
        )
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.primary
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(titleText) {
                    start.linkTo(backIcon.end, margin = 8.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(favoriteIcon.start, margin = 8.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Icon(
            imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = stringResource(if (isFavorite) R.string.favorite_active else R.string.favorite_inactive),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(8.dp)
                .clickable { onClickFavorite() }
                .constrainAs(favoriteIcon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, margin = 8.dp)
                }
        )

        if (isFavorite) {
            onFavorite()
        }
    }
}

@Composable
fun AnimeDetailContent(
    image: String,
    score: Double,
    ranked: Int,
    popularity: Int,
    members: Int,
    info: List<InfoItem>,
    descriptions: List<InfoItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        TitleDivider(title = stringResource(R.string.information))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.Top)
            ) {
                info.forEach {
                    Info(title = stringResource(it.title), value = it.value)
                }
            }
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            Box(
                modifier = Modifier
                    .size(width = 140.dp, height = 196.dp)
                    .clip(Shapes.medium)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 140.dp, height = 196.dp)
                        .clip(Shapes.medium)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(BlackOpacity)
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clip(Shapes.medium)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "score",
                        tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = score.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatisticCard(
                title = stringResource(id = R.string.ranked),
                value = "#$ranked",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
            StatisticCard(
                title = stringResource(id = R.string.popularity),
                value = "#$popularity",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
            StatisticCard(
                title = stringResource(id = R.string.members),
                value = members.toString(),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        descriptions.forEach {
            Description(title = stringResource(it.title), value = it.value)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyTopBarPreview() {
    DicodingJetpackComposeSubmissionTheme {
        DetailTopBar(
            title = "Lorem Ipsum Dolor Sit Amet",
            isFavorite = false,
            onBackClick = {},
            onClickFavorite = {}
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4
)
@Composable
fun AnimeDetailContentPreview() {
    DicodingJetpackComposeSubmissionTheme {
        AnimeDetailContent(
            image = "image",
            score = 10.0,
            ranked = 1,
            popularity = 1,
            members = 100000,
            info = listOf(
                InfoItem(
                    title = R.string.ranked,
                    value = "1"
                ),
                InfoItem(
                    title = R.string.popularity,
                    value = "1"
                )
            ),
            descriptions = listOf(
                InfoItem(
                    title = R.string.synopsis,
                    value = "Welcome to your personal dashboard, where you can find an introduction to how GitHub works, tools to help you build software, and help merging your first lines of code."
                ),
                InfoItem(
                    title = R.string.background,
                    value = "Welcome to your personal dashboard, where you can find an introduction to how GitHub works, tools to help you build software, and help merging your first lines of code."
                )
            ),
            modifier = Modifier.fillMaxSize(),
        )
    }
}