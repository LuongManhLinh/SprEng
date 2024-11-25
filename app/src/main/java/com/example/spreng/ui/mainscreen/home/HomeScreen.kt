package com.example.spreng.ui.mainscreen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLessonStarted: () -> Unit = { },
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var topPadding by remember { mutableStateOf(0.dp) }

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                userName = uiState.userName,
                userXp = uiState.userXp.toString(),
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

                LessonImage(
                    lessonUI = lessonUI,
                    lessonIdx = idx,
                    onPressChanged = viewModel::onPressChanged,
                    onLessonStarted = onLessonStarted,
                    onOpeningCompleted = { viewModel.onCardOpeningCompleted(idx) },
                    onClosingCompleted = { viewModel.onCardClosingCompleted(idx) }
                )
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




@Preview(showBackground = false)
@Composable
private fun Preview() {
    HomeScreen()
}
