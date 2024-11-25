package com.example.spreng.ui.studyscreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import com.example.spreng.ui.studyscreen.answer.micro.TalkingScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerFillingScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerSequenceScreen
import com.example.spreng.ui.studyscreen.answer.writing.BaseWritingScreen
import com.example.spreng.ui.studyscreen.question.listening.BaseListeningQuestionScreen
import com.example.spreng.ui.studyscreen.question.text.QuestionText
import com.example.spreng.ui.studyscreen.result.ResultScreen

@Composable
fun StudyFlowScreen(
    modifier: Modifier = Modifier,
    viewModel: StudyFlowViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    if (uiState.isLessonDone) {
        ResultScreen(
            numCorrect = uiState.numCorrect,
            numTotal = uiState.totalChallenge
        )
        return
    }

    BaseStudyScreen(
        learningProgress = uiState.learningProgress,
        questionTitle = uiState.title,
        isDone = uiState.isDone,
        onCancelling = {
            viewModel.exit(context)
        },
        onBottomButtonPressed = {
            if (uiState.isDone) {
                viewModel.nextChallenge()
            } else {
                viewModel.complete()
            }
        }
    ) {
        Column {
            when(uiState.answerUIState) {
                is AnswerUIState.WordPickerFilling -> {

                    QuestionText(
                        modifier = modifier
                            .fillMaxWidth(),
                        questionContent = (
                                uiState.questionUIState as QuestionUIState.Text
                                ).questionContent,
                    )

                    WordPickerFillingScreen(
                        modifier = modifier.fillMaxWidth().weight(1f),
                        sentenceUI = (
                                uiState.answerUIState as AnswerUIState.WordPickerFilling
                                ).sentenceUI,
                        unselectedWords = (
                                uiState.answerUIState as AnswerUIState.WordPickerFilling
                                ).unselectedWords,
                        onUnselectedWordClick = { viewModel.clickUnselectedWord(it) },
                        onSelectedWordClick = { viewModel.clickSelectedWord(it) }
                    )
                }


                is AnswerUIState.WordPickerSequence -> {
                    QuestionText(
                        modifier = modifier
                            .fillMaxWidth(),
                        questionContent = (
                                uiState.questionUIState as QuestionUIState.Text
                                ).questionContent,
                    )

                    WordPickerSequenceScreen(
                        modifier = modifier.fillMaxWidth().weight(1f),
                        selectedWords = (
                                uiState.answerUIState as AnswerUIState.WordPickerSequence
                                ).selectedWords,
                        unselectedWords = (
                                uiState.answerUIState as AnswerUIState.WordPickerSequence
                                ).unselectedWords,
                        onUnselectedWordClick = { viewModel.clickUnselectedWord(it) },
                        onSelectedWordClick = { viewModel.clickSelectedWord(it) }
                    )
                }

                is AnswerUIState.Talking -> {
                    QuestionText(
                        modifier = modifier
                            .fillMaxWidth(),
                        questionContent = (
                                uiState.questionUIState as QuestionUIState.Text
                                ).questionContent,
                    )
                    TalkingScreen(
                        modifier = modifier.fillMaxWidth().weight(1f),
                        inputAnswer = (uiState.answerUIState as AnswerUIState.Talking).answerTalking,
                        saveInputAnswer = {viewModel.updateAnswerTalking(it)}
                    )
                }

                is AnswerUIState.TextTyping -> {
                    BaseListeningQuestionScreen(
                        modifier = modifier.fillMaxWidth().weight(0.3f),
                        context = context,
                        sentence = (uiState.questionUIState as QuestionUIState.Listening).questionContent
                    )
                    BaseWritingScreen(
                        modifier = modifier.fillMaxWidth().weight(0.7f),
                        inputAnswer = (uiState.answerUIState as AnswerUIState.TextTyping).answerWriting,
                        saveInputAnswer = {viewModel.updateAnswerWriting(it)}
                    )
                }
            }

            PopupResult(
                modifier = modifier.padding(
                    start = dimensionResource(R.dimen.large),
                    end = dimensionResource(R.dimen.large),
                ),
                isVisible = uiState.isDone,
                isCorrect = uiState.isCorrect,
                correctAnswer = uiState.correctAnswer
            )
        }
    }

}


@Composable
private fun PopupResult(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    isCorrect: Boolean,
    correctAnswer: String? = null
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        modifier = modifier.fillMaxWidth().alpha(
            if (isVisible) 1f else 0f
        )
    ) {
        CustomRoundedBorderBox(
            cornerRadius = dimensionResource(R.dimen.medium),
            borderColor = Color.Gray,
            bottomBorderWidth = dimensionResource(R.dimen.small),
            topBorderWidth = dimensionResource(R.dimen.very_tiny),
            startBorderWidth = dimensionResource(R.dimen.tiny),
            endBorderWidth = dimensionResource(R.dimen.very_tiny),
        ) {
            Column (
                modifier = Modifier
                    .height(dimensionResource(R.dimen.popup_height))
                    .fillMaxWidth()
                    .background(
                        if (isCorrect) Color.Green else Color.Red
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isCorrect) "Chính xác" else "Không chính xác",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.small))
                )
                if (!isCorrect && correctAnswer != null) {
                    Text(
                        text = correctAnswer,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.small)),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun StudyFlowScreenPreview() {
    StudyFlowScreen()
}

@Preview(showBackground = true)
@Composable
private fun ResultPopupPreview() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        PopupResult(
            isVisible = true,
            isCorrect = true
        )
        PopupResult(
            isVisible = true,
            isCorrect = false,
            correctAnswer = "This is the correct answer"
        )
    }

}