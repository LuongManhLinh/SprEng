package com.example.spreng.ui.studyscreen.answer.wordpicker

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.ui.studyscreen.SelectedWord
import com.example.spreng.ui.studyscreen.UnselectedWord


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordPickerSequenceScreen(
    modifier: Modifier = Modifier,
    unselectedWords: List<UnselectedWord>,
    selectedWords: List<SelectedWord>,
    onUnselectedWordClick: (Int) -> Unit,
    onSelectedWordClick: (SelectedWord) -> Unit
) {
    BaseWordPickerScreen(
        modifier = modifier,
        unselectedWords = unselectedWords,
        onUnselectedWordClick = onUnselectedWordClick
    ) { contentModifier ->
        FlowRow(
            modifier = contentModifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            selectedWords.forEach { word ->
                WordItem(
                    word = word.word,
                    onClick = { onSelectedWordClick(word) }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun WordPickerPreview() {
    WordPickerSequenceScreen(
        unselectedWords = listOf(
            UnselectedWord("this"),
            UnselectedWord("these"),
            UnselectedWord("an"),
            UnselectedWord("sentence"),
            UnselectedWord("expensive"),
            UnselectedWord("beautiful"),
        ),
        selectedWords = listOf(
            SelectedWord("is", 1),
            SelectedWord("a", 2)
        ),
        onUnselectedWordClick = {},
        onSelectedWordClick = {}
    )
}