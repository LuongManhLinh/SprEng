package com.example.spreng.ui.studyscreen

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R

import com.example.spreng.ui.studyscreen.question.listening.BaseListeningQuestionScreen
import com.example.spreng.speech2Text.SpeechRecognizer
import com.example.spreng.ui.studyscreen.answer.micro.TalkingScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerFillingScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerSequenceScreen
import com.example.spreng.ui.studyscreen.answer.writing.BaseWritingScreen

@Composable
fun StudyFlowScreen(
    modifier: Modifier = Modifier,
    viewModel: StudyFlowViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

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
                            .fillMaxWidth()
                            .weight(0.3f),
                        questionContent = (
                                uiState.questionUIState as QuestionUIState.Text
                                ).questionContent,
                    )

                    WordPickerFillingScreen(
                        modifier = modifier.fillMaxWidth().weight(0.7f),
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
                            .fillMaxWidth()
                            .weight(0.3f),
                        questionContent = (
                                uiState.questionUIState as QuestionUIState.Text
                                ).questionContent,
                    )

                    WordPickerSequenceScreen(
                        modifier = modifier.fillMaxWidth().weight(0.7f),
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
                            .fillMaxWidth()
                            .weight(0.3f),
                        questionContent = (
                                uiState.questionUIState as QuestionUIState.Text
                                ).questionContent,
                    )
                    TalkingScreen(
                        modifier = modifier.fillMaxWidth().weight(0.7f),
                        context = context,
                        inputAnswer = (uiState.answerUIState as AnswerUIState.Talking).answerTalking,
                        saveInputAnswer = {viewModel.updateAnswerTalking(it)}
                    )
                }
                is AnswerUIState.TextTyping -> {
                    Log.i("QS", (uiState.questionUIState as QuestionUIState.Listening).questionContent)
                    Log.i("AS", (uiState.answerUIState as AnswerUIState.TextTyping).answerWriting)
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

            ResultPopup(
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
private fun ResultPopup(
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
        Column (
            modifier = Modifier
                .height(dimensionResource(R.dimen.popup_height))
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = dimensionResource(R.dimen.small),
                        topEnd = dimensionResource(R.dimen.small)
                    )
                )
                .border(
                    width = dimensionResource(R.dimen.very_tiny),
                    color = Color.Gray,
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(R.dimen.small),
                        topEnd = dimensionResource(R.dimen.small)
                    )
                )
                .background(
                    if (isCorrect) Color.Green else Color.Red
                ),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = if (isCorrect) "Chính xác" else "Không chính xác",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.small))
                    .align(Alignment.CenterHorizontally)
            )
            if (!isCorrect && correctAnswer != null) {
                Text(
                    text = correctAnswer,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.small))
                        .fillMaxWidth()
                        .align(Alignment.Start),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
private fun QuestionText(
    modifier: Modifier = Modifier,
    questionContent: String
) {
    Box(
        modifier = modifier
            .padding(dimensionResource(R.dimen.small))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small)))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(dimensionResource(R.dimen.small))
            )
    ) {
        Text(
            text = questionContent,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.small))
        )
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
        ResultPopup(
            isVisible = true,
            isCorrect = true
        )
        ResultPopup(
            isVisible = true,
            isCorrect = false,
            correctAnswer = "This is the correct answer"
        )
    }

}