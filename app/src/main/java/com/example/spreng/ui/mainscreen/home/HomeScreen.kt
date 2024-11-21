package com.example.spreng.ui.mainscreen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLessonStarted: () -> Unit = { },
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var topPadding by remember { mutableStateOf(0.dp) }

//    val systemUiController = rememberSystemUiController()
//
//    SideEffect {
//        systemUiController.setStatusBarColor(
//            color = Color.Red
//        )
//    }

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                userName = uiState.userName,
                userXp = uiState.userXp.toString(),
            )
        },
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

                var isPressed by remember { mutableStateOf(false) }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.medium))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.small)))
                ) {
                    Row {
                        Spacer(Modifier.weight(lessonUI.leftWeight))

                        Image(
                            painter = painterResource(
                                if (isPressed) {
                                    R.drawable.lesson_ic_pressed
                                } else {
                                    R.drawable.lesson_ic
                                }
                            ),
                            contentDescription =
                            stringResource(R.string.lesson_img_cnt_desc, idx + 1),
                            modifier = Modifier
                                .pointerInput(Unit) {
                                    awaitPointerEventScope {
                                        while (true) {
                                            val event = awaitPointerEvent()
                                            isPressed = handlePressAction(
                                                event = event,
                                                size = size,
                                                actionIfPressed = {
                                                    if (lessonUI.cardState
                                                        == LessonCardState.HIDING) {
                                                        viewModel.updateLessonCardState(
                                                            idx, LessonCardState.OPENING
                                                        )
                                                    }
                                                    else if (lessonUI.cardState
                                                        == LessonCardState.SHOWING) {
                                                        viewModel.updateLessonCardState(
                                                            idx, LessonCardState.CLOSING
                                                        )
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                        )

                        Spacer(Modifier.weight(lessonUI.rightWeight))
                    }

                    LessonBox(
                        isShowingBox = lessonUI.cardState == LessonCardState.SHOWING
                                || lessonUI.cardState == LessonCardState.OPENING,
                        info = "This is the lesson ${lessonUI.id}",
                        onLessonStarted = onLessonStarted,
                        onOpeningCompleted = {
                            viewModel.updateLessonCardState(idx, LessonCardState.SHOWING)
                        },
                        onClosingCompleted = {
                            viewModel.updateLessonCardState(idx, LessonCardState.HIDING)
                        }
                    )
                }
            }
        }
    }

    StudyProgressBar(
        modifier = Modifier.padding(
            top = topPadding
        ),
        numCompletedLesson = uiState.numCompletedLesson,
        numTotalLesson = uiState.lessonList.size
    )

}


private fun handlePressAction(
    event: PointerEvent,
    size: IntSize,
    actionIfPressed: () -> Unit
) : Boolean {
    val eventPosition = event.changes.firstOrNull()?.position

    if (event.changes.firstOrNull()?.pressed == true && eventPosition != null) {

        val w = size.width
        val h = size.height
        val eX = eventPosition.x
        val eY = eventPosition.y

        val isPressed = eX >= 0 && eY >= 0 && eX <= w && eY <= h
        if (isPressed) {
            actionIfPressed()
        }

        return isPressed
    }

    return false
}


@Composable
private fun LessonBox(
    modifier: Modifier = Modifier,
    isShowingBox: Boolean,
    info: String,
    onLessonStarted: () -> Unit,
    onOpeningCompleted: () -> Unit,
    onClosingCompleted: () -> Unit
) {

    LaunchedEffect(isShowingBox) {
        if (isShowingBox) {
            delay(800)
            onOpeningCompleted()
        } else {
            delay(600)
            onClosingCompleted()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = if (isShowingBox) {
            Alignment.Start
        } else {
            Alignment.End
        }
    ) {
        AnimatedVisibility(
            visible = isShowingBox,
            enter = expandHorizontally(
                animationSpec = tween(
                    durationMillis = 800
                ),
                expandFrom = Alignment.Start
            ),
            exit = shrinkHorizontally(
                animationSpec = tween(
                    durationMillis = 500
                ),
                shrinkTowards = Alignment.End
            )
        ) {
            Box(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.tiny))
                    .fillMaxWidth()
                    .background(Color.Black)
            )
        }
    }

    AnimatedVisibility(
        visible = isShowingBox,
        enter = expandVertically(
            animationSpec = tween(
                durationMillis = 700
            )
        ),
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 600
            )
        )
    ) {
        LessonContent(
            info = info,
            onLessonClicked = onLessonStarted
        )
    }
}


@Composable
private fun LessonContent(
    info: String,
    onLessonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Color.Green)
    ) {
        Text(
            text = info,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(dimensionResource(R.dimen.medium))
        )
        Row {
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { onLessonClicked() },
                modifier = Modifier.padding(dimensionResource(R.dimen.small))
            ) {
                Text("Bắt đầu")
            }
        }
    }
}



@Preview(showBackground = false)
@Composable
private fun Preview() {
    HomeScreen(viewModel = HomeViewModel())
}
