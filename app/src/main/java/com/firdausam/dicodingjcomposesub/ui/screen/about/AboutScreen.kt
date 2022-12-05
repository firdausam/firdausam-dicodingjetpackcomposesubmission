package com.firdausam.dicodingjcomposesub.ui.screen.about

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onSocmedIconClick: (String) -> Unit
) {
    Box(modifier.fillMaxSize()) {
        AboutContent(
            name = stringResource(R.string.my_name),
            email = stringResource(R.string.my_email_dicoding),
            imgProfile = R.drawable.img_profil,
            dicodingUrl = stringResource(R.string.my_dicoding),
            linkedInUrl = stringResource(R.string.my_linkedin),
            githubUrl = stringResource(R.string.my_dicoding),
            onSocmedIconClick = onSocmedIconClick,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
private fun AboutContent(
    name: String,
    email: String,
    @DrawableRes imgProfile: Int,
    dicodingUrl: String,
    linkedInUrl: String,
    githubUrl: String,
    modifier: Modifier = Modifier,
    onSocmedIconClick: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imgProfile),
            contentDescription = stringResource(R.string.profile),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Black
            )
        )
        Text(
            text = email,
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Light
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_dicoding),
                contentDescription = stringResource(R.string.dicoding),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable(onClick = { onSocmedIconClick(dicodingUrl) })
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_linkedin),
                contentDescription = stringResource(R.string.linkedin),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable(onClick = { onSocmedIconClick(linkedInUrl) })
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_github),
                contentDescription = stringResource(R.string.github),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable(onClick = { onSocmedIconClick(githubUrl) }),
                tint = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    DicodingJetpackComposeSubmissionTheme {
        AboutScreen(onSocmedIconClick = {})
    }
}