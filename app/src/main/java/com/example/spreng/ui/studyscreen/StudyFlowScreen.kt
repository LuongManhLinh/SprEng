package com.example.spreng.ui.studyscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerFillingScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerSequenceScreen

@Composable
fun StudyFlowScreen(
    modifier: Modifier = Modifier,
    viewModel: StudyFlowViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    BaseStudyScreen(
        learningProgress = 0.5F,
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

                is AnswerUIState.Talking -> TODO()
                is AnswerUIState.TextTyping -> TODO()
            }

            AnimatedVisibility(
                visible = uiState.isDone,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            ) {
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
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