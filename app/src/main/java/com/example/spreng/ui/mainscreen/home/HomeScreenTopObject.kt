package com.example.spreng.ui.mainscreen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
internal fun HomeTopBar(
    modifier: Modifier = Modifier,
    userName: String,
    userXp: String,
    onAvatarClicked: () -> Unit
) {
    CustomRoundedBorderBox(
        modifier = modifier
            .padding(
                top = dimensionResource(R.dimen.tiny),
                start = dimensionResource(R.dimen.tiny),
                end = dimensionResource(R.dimen.tiny)
            ),
        cornerRadius = dimensionResource(R.dimen.small),
        startBorderWidth = dimensionResource(R.dimen.tiny),
        bottomBorderWidth = dimensionResource(R.dimen.small),
        containerColor = colorResource(R.color.teal_200),
        borderColor = colorResource(R.color.gray_teal)
    ) {
        Row(
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.small),
                end = dimensionResource(R.dimen.small),
                top = dimensionResource(R.dimen.tiny),
                bottom = dimensionResource(R.dimen.tiny)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.sample_avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.middle_large))
                    .clip(
                        RoundedCornerShape(dimensionResource(R.dimen.large))
                    )
                    .clickable {
                        onAvatarClicked()
                    },
                contentScale = ContentScale.Crop
            )
            Text(
                text = userName,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.small))
                    .widthIn(max = 200.dp)
            )
            Spacer(Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.xp),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.middle_large))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.large))),
                contentScale = ContentScale.Crop
            )
            Text(
                text = userXp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(R.color.xp),
                modifier = Modifier.padding(start = dimensionResource(R.dimen.small))
            )
        }

    }
}

@Composable
internal fun StudyProgressBar(
    modifier: Modifier = Modifier,
    numCompletedLesson: Int,
    numTotalLesson: Int,
    appear: Boolean = false,
    onAppearChange: (Boolean) -> Unit
) {

    Row(
        modifier = modifier
            .padding(top = dimensionResource(R.dimen.small))
            .height(dimensionResource(R.dimen.middle_large)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ExpandButton(
            modifier = Modifier.padding(start = dimensionResource(R.dimen.small)),
            icon = if (appear) {
                Icons.AutoMirrored.Filled.ArrowBackIos
            } else {
                Icons.AutoMirrored.Filled.ArrowForwardIos
            }
        ) {
            onAppearChange(!appear)
        }

        StudyProgressBarContent(
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.small))
                .clickable(
                    indication = null,
                    interactionSource = null
                ) {
                    onAppearChange(false)
                },
            appear = appear,
            numCompletedLesson = numCompletedLesson,
            numTotalLesson = numTotalLesson
        )
    }

}

@Composable
private fun ExpandButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = Color.Black,
        modifier = modifier.clickable(
            interactionSource = null,
            indication = null
        ) {
            onClick()
        }
    )
}

@Composable
private fun StudyProgressBarContent(
    modifier: Modifier = Modifier,
    appear: Boolean = false,
    numCompletedLesson: Int,
    numTotalLesson: Int
) {
    val smallDimen = dimensionResource(R.dimen.small)
    AnimatedVisibility(
        visible = appear,
        enter = expandHorizontally(
            animationSpec = tween(
                durationMillis = 400
            )
        ),
        exit = shrinkHorizontally (
            animationSpec = tween(
                durationMillis = 400
            )
        ),
        modifier = modifier
    ) {
        CustomRoundedBorderBox(
            cornerRadius = smallDimen,
            startBorderWidth = dimensionResource(R.dimen.very_tiny),
            bottomBorderWidth = dimensionResource(R.dimen.tiny),
            borderColor = Color(0, 100, 0)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val size = Size(
                            width = size.width * numCompletedLesson / numTotalLesson,
                            height = size.height
                        )
                        drawRect(
                            color = Color.Green,
                            size = size,
                        )
                    }
            ) {
                Spacer(Modifier.weight(0.1f))
                Text(
                    text = stringResource(R.string.progress),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "$numCompletedLesson/$numTotalLesson",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(Modifier.weight(0.1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeTopBar(
            userName = "Nguyễn Trần Hoàng Văn A",
            userXp = "1000",
            onAvatarClicked = {}
        )
        StudyProgressBar(
            numCompletedLesson = 5,
            numTotalLesson = 10,
            onAppearChange = {}
        )
    }

}

