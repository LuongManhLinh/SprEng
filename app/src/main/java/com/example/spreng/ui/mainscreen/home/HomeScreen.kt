package com.example.spreng.ui.mainscreen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLessonStarted: () -> Unit = { },
    onAvatarClicked: () -> Unit = { },
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var topPadding by remember { mutableStateOf(0.dp) }


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
                        onLessonStarted = onLessonStarted,
                        onOpeningCompleted = { viewModel.onCardOpeningCompleted(idx) },
                        onClosingCompleted = { viewModel.onCardClosingCompleted(idx) },
                        modifier = Modifier
                    )

                    if (isCurrentLesson) {
                        Row {
                            Spacer(Modifier.weight(lessonUI.leftWeight))
                            FireAnimation()
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
    modifier: Modifier = Modifier
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
