package com.example.spreng.ui.mainscreen.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLessonClicked: () -> Unit = { },
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                userName = uiState.userName,
                userXp = uiState.userXp.toString(),
                userProgress = "${uiState.numCompletedLesson}/${uiState.numTotalLesson}"
            )
        }
    ) { innerPadding ->
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
                            contentDescription = null,
                            modifier = Modifier
                                .pointerInput(Unit) {
                                    awaitPointerEventScope {
                                        while (true) {
                                            val event = awaitPointerEvent()
                                            val eventPosition = event.changes.firstOrNull()?.position

                                            if (event.changes.firstOrNull()?.pressed == true && eventPosition != null) {

                                                val w = size.width
                                                val h = size.height
                                                val eX = eventPosition.x
                                                val eY = eventPosition.y

                                                isPressed = eX >= 0 && eY >= 0 && eX <= w && eY <= h
                                                if (isPressed) {
                                                    if (lessonUI.cardState == LessonCardState.HIDING) {
                                                        viewModel.updateLessonCardState(idx, LessonCardState.OPENING)
                                                    }
                                                    else if (lessonUI.cardState == LessonCardState.SHOWING) {
                                                        viewModel.updateLessonCardState(idx, LessonCardState.CLOSING)
                                                    }
                                                    Log.d("HomeScreen", "Lesson ${lessonUI.cardState}")
                                                }

                                            } else {

                                                isPressed = false

                                            }
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
                        onLessonClicked = onLessonClicked,
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
}

@Composable
private fun HomeTopBar(
    modifier: Modifier = Modifier,
    userName: String,
    userXp: String,
    userProgress: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        HomeTopBarfInfo(
            userName = userName,
            userXp = userXp
        )
        HomeTopBarProgress(
            userProgress = userProgress
        )
    }
}

@Composable
private fun HomeTopBarfInfo(
    modifier: Modifier = Modifier,
    userName: String,
    userXp: String
) {
    Row(
        modifier = modifier
            .padding(
                top = dimensionResource(R.dimen.tiny),
                start = dimensionResource(R.dimen.tiny),
                end = dimensionResource(R.dimen.tiny)
            )
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small)))
            .background(Color.LightGray)
            .padding(dimensionResource(R.dimen.small))
        ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.sample_avatar),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.middle_large))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.large))
                )
            ,
            contentScale = ContentScale.Crop
        )
        Text(
            text = userName,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.small))
        )
        Spacer(Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.xp),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.middle_large))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.large)))
            ,
            contentScale = ContentScale.Crop
        )
        Text(
            text = userXp,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0, 130, 0),
            modifier = Modifier.padding(start = dimensionResource(R.dimen.small))
        )
    }
}

@Composable
private fun HomeTopBarProgress(
    modifier: Modifier = Modifier,
    userProgress: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .width(200.dp)
            .clip(
                RoundedCornerShape(
                    bottomStart = dimensionResource(R.dimen.small),
                    bottomEnd = dimensionResource(R.dimen.small)
                )
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(
                    bottomStart = dimensionResource(R.dimen.small),
                    bottomEnd = dimensionResource(R.dimen.small)
                )
            )
            .background(Color.Magenta)
    ) {
        Spacer(Modifier.weight(0.1f))
        Text(
            text = stringResource(R.string.progress),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = userProgress,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(Modifier.weight(0.1f))
    }
}


@Composable
private fun LessonBox(
    modifier: Modifier = Modifier,
    isShowingBox: Boolean,
    info: String,
    onLessonClicked: () -> Unit,
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
            onLessonClicked = onLessonClicked
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

