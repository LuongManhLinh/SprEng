package com.example.spreng.ui.studyscreen.answer.wordpicker

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordPickerSequenceScreen(
    modifier: Modifier = Modifier,
    viewModel: WordPickerSequenceViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    BaseWordPickerScreen(
        modifier = modifier,
        unselectedWords = uiState.unselectedWords,
        onUnselectedWordClick = { idx ->
            viewModel.clickUnselectedWord(idx)
        }
    ) { contentModifier ->
        FlowRow(
            modifier = contentModifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            uiState.selectedWords.forEach { word ->
                WordItem(
                    word = word.word,
                    onClick = {
                        viewModel.clickSelectedWord(word)
                    }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun WordPickerPreview() {
    WordPickerSequenceScreen(
        viewModel = WordPickerSequenceViewModel()
    )
}