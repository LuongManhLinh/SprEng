package com.example.spreng.ui.mainscreen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.spreng.R
import com.example.spreng.data.preferences.UserManager


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLessonStarted: (Int) -> Unit = { },
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.factory),
    onAvatarClicked: () -> Unit = { },
) {
    val uiState by viewModel.uiState.collectAsState()

    var topPadding by remember { mutableStateOf(0.dp) }
    val context = LocalContext.current
    val currentUserId = UserManager.getUserId(context)

    LaunchedEffect(Unit) {
        viewModel.updateAccount(currentUserId)
    }

    Scaffold(
        modifier = modifier,

        topBar = {
            HomeTopBar(
                userName = uiState.userName,
                userXp = if (uiState.userXp < 10000) {
                    uiState.userXp.toString()
                } else {
                    "9999+"
                },
                onAvatarClicked = onAvatarClicked
            )
        },

        containerColor = colorResource(R.color.container)

    ) { innerPadding ->

        topPadding = innerPadding.calculateTopPadding()

        LazyColumn(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                )
                .fillMaxSize()
        ) {
            items(uiState.lessonList.size) { idx ->
                val lessonUI = uiState.lessonList[idx]
                val isCurrentLesson = idx == uiState.numCompletedLesson
                Box {
                    LessonUI(
                        lessonUIState = lessonUI,
                        lessonIdx = idx,
                        isCurrentLesson = isCurrentLesson,
                        onPressChanged = viewModel::onPressChanged,
                        onLessonStarted = {
                            onLessonStarted(idx)
                        },
                        onOpeningCompleted = { viewModel.onCardOpeningCompleted(idx) },
                        onClosingCompleted = { viewModel.onCardClosingCompleted(idx) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (isCurrentLesson) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(Modifier.weight(lessonUI.leftWeight))
                            Box(
                                modifier = Modifier
                                    .padding(
                                        start = dimensionResource(R.dimen.medium),
                                        end = dimensionResource(R.dimen.medium)
                                    )
                                    .width(84.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                FireAnimation()
                            }
                            Spacer(Modifier.weight(lessonUI.rightWeight))
                        }
                    }
                }
            }
        }
    }

    StudyProgressBar(
        modifier = Modifier.padding(
            top = topPadding
        ),
        numCompletedLesson = uiState.numCompletedLesson,
        numTotalLesson = uiState.lessonList.size,
        appear = uiState.isProgressBarAppeared,
        onAppearChange = viewModel::onProgressBarAppearanceChanged
    )

}


@Composable
private fun FireAnimation(
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.fire_animation)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(dimensionResource(R.dimen.large))
    )
}


@Preview(showBackground = false)
@Composable
private fun Preview() {
    HomeScreen()
}
