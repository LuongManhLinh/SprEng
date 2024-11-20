package com.example.spreng.ui.studyscreen.answer.writing

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BaseWritingScreen(
    baseWritingScreenViewModel: BaseWritingScreenViewModel = viewModel()
) {
    val uiState by baseWritingScreenViewModel.uiState.collectAsState()
    Box() {
        TextField(
            value = uiState.answerWriting,
            onValueChange = {
                baseWritingScreenViewModel.updateAnswer(
                    it
                )
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BasePre() {
    BaseWritingScreen()
}
