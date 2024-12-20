package com.example.spreng.ui.studyscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spreng.R
import com.example.spreng.data.preferences.UserManager
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import com.example.spreng.ui.studyscreen.answer.micro.SpeakingScreen
import com.example.spreng.ui.studyscreen.answer.multichoice.MultiChoiceScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerFillingScreen
import com.example.spreng.ui.studyscreen.answer.wordpicker.WordPickerSequenceScreen
import com.example.spreng.ui.studyscreen.answer.writing.BaseWritingScreen
import com.example.spreng.ui.studyscreen.question.listening.BaseListeningQuestionScreen
import com.example.spreng.ui.studyscreen.question.text.QuestionText
import com.example.spreng.ui.studyscreen.result.ResultScreen

@Composable
fun StudyFlowScreen(
    lessonId: Int,
    modifier: Modifier = Modifier,
    viewModel: StudyFlowViewModel = viewModel(factory = StudyFlowViewModel.factory)
) {

    LaunchedEffect(Unit) {
        viewModel.initialize(lessonId)
    }

    val uiState by viewModel.uiState.collectAsState()
    val popupUIState by viewModel.popupUiState.collectAsState()
    val context = LocalContext.current

    val userId = UserManager.getUserId(context)
    if (uiState.isLessonDone) {
        ResultScreen(
            numCorrect = uiState.numCorrect,
            numTotal = uiState.totalChallenge
        )
        viewModel.updateCompletedLesson(userId, lessonId)
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
        },
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .clickable(
                    interactionSource = null,
                    indication = null
                ) {
                    if (uiState.isDone) viewModel.changeResultPopupVisibility()
                }
        ) {

            Column {

                when (uiState.questionUIState) {

                    is QuestionUIState.Text -> {
                        QuestionText(
                            modifier = Modifier
                                .fillMaxWidth(),
                            questionContent = (
                                    uiState.questionUIState as QuestionUIState.Text
                                    ).questionContent,
                        )
                    }

                    is QuestionUIState.Listening -> {
                        BaseListeningQuestionScreen(
                            modifier = Modifier.fillMaxWidth().weight(0.5f),
                            context = context,
                            sentence = (
                                    uiState.questionUIState as QuestionUIState.Listening
                                    ).questionContent
                        )
                    }

                }


                when (uiState.answerUIState) {

                    is AnswerUIState.WordPickerFilling -> {

                        WordPickerFillingScreen(
                            modifier = Modifier.fillMaxWidth().weight(1f),
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

                        WordPickerSequenceScreen(
                            modifier = Modifier.fillMaxWidth().weight(1f),
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

                        SpeakingScreen(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            inputAnswer = (uiState.answerUIState as AnswerUIState.Talking).answerTalking,
                            saveInputAnswer = { viewModel.updateAnswerTalking(it) }
                        )

                    }


                    is AnswerUIState.TextTyping -> {

                        Spacer(Modifier.height(48.dp))
                        BaseWritingScreen(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            inputAnswer = (uiState.answerUIState as AnswerUIState.TextTyping).answerWriting,
                            saveInputAnswer = { viewModel.updateAnswerWriting(it) }
                        )

                    }

                    is AnswerUIState.MultiChoice -> {
                        val answerUIState = uiState.answerUIState as AnswerUIState.MultiChoice
                        MultiChoiceScreen(
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            choices = answerUIState.choices,
                            selectedIndex = answerUIState.selectedIdx,
                            onChoiceClick = { viewModel.updateAnswerMultiChoice(it) }
                        )
                    }
                }

            }

            PopupResult(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.large),
                        end = dimensionResource(R.dimen.large),
                    )
                    .align(Alignment.BottomCenter),
                isVisible = popupUIState.isVisible,
                isCorrect = popupUIState.isCorrect,
                correctAnswer = popupUIState.correctAnswer,
                onClicked = {
                    viewModel.changeResultPopupVisibility()
                }
            )

        }
    }
}


@Composable
private fun PopupResult(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    isCorrect: Boolean,
    correctAnswer: String? = null,
    onClicked: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = null,
                indication = null,
            ) { onClicked() }
    ) {
        CustomRoundedBorderBox(
            cornerRadius = dimensionResource(R.dimen.medium),
            borderColor = if (isCorrect) {
                colorResource(R.color.success_border)
            } else {
                colorResource(R.color.error_border)
            },
            bottomBorderWidth = dimensionResource(R.dimen.small)
        ) {
            Column(
                modifier = Modifier
                    .heightIn(min = dimensionResource(R.dimen.popup_height))
                    .fillMaxWidth()
                    .background(
                        if (isCorrect) {
                            colorResource(R.color.success)
                        } else {
                            colorResource(R.color.error)
                        }
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isCorrect) "Chính xác" else "Không chính xác",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.small))
                )
                if (!isCorrect && correctAnswer != null) {
                    Text(
                        text = "\"$correctAnswer\"",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.medium)),
                    )
                }
            }
        }

    }
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
            correctAnswer = "This is the correct answer and this will be so long to see how it will be displayed"
        )
    }

}