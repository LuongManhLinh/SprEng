package com.example.spreng.ui.studyscreen.answer.wordpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.ui.studyscreen.SelectedWord
import com.example.spreng.ui.studyscreen.UnselectedWord

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordPickerFillingScreen(
    modifier: Modifier = Modifier,
    unselectedWords: List<UnselectedWord>,
    sentenceUI: List<Any?>,
    onUnselectedWordClick: (Int) -> Unit,
    onSelectedWordClick: (SelectedWord) -> Unit
) {
    var cardMaxHeight by remember { mutableStateOf(40.dp) }
    var cardMaxWidth by remember { mutableStateOf(0.dp) }
    var textHeight by remember { mutableStateOf(0.dp) }

    BaseWordPickerScreen(
        unselectedWords = unselectedWords,
        onUnselectedWordClick = onUnselectedWordClick,
        modifier = modifier,
        getCardSize = { width, height ->
            if (width > cardMaxWidth) {
                cardMaxWidth = width
            }

            if (height > cardMaxHeight) {
                cardMaxHeight = height
            }
        }
    ) { contentModifier ->
        FlowRow(
            modifier = contentModifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.small))
            ,
            horizontalArrangement = Arrangement.Start
        ) {
            sentenceUI.forEach { value ->
                when (value) {

                    is String -> {
                        UnmaskedWord(
                            word = value,
                            cardHeight = cardMaxHeight,
                            setTextHeight = { textHeight = it }
                        )
                    }

                    is SelectedWord -> {
                        WordItem(
                            word = value.word,
                            onClick = {
                                onSelectedWordClick(value)
                            },
                            modifier = Modifier.width(cardMaxWidth),
                            widthDp = cardMaxWidth
                        )
                    }

                    else -> {
                        Blank(
                            containerHeight = cardMaxHeight,
                            textHeight = textHeight,
                            length = cardMaxWidth
                        )
                    }

                }

            }
        }
    }
}

@Composable
private fun UnmaskedWord(
    modifier: Modifier = Modifier,
    word: String,
    cardHeight: Dp,
    setTextHeight: (Dp) -> Unit
) {
    val currentDensity = LocalDensity.current

    Box(
        modifier = modifier.height(cardHeight),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = word,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp,
            modifier = Modifier.onGloballyPositioned {
                with(currentDensity) {
                    setTextHeight(it.size.height.toDp())
                }
            }
        )
    }

}

@Composable
private fun Blank(
    containerHeight: Dp,
    textHeight: Dp,
    length: Dp,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.height(containerHeight)
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.height(textHeight)
        ) {
            Box(
                modifier = Modifier
                    .size(length, 1.dp)
                    .background(Color.Gray)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WordPickerFillPreview() {
    WordPickerFillingScreen(
        unselectedWords = listOf(
            UnselectedWord("this"),
            UnselectedWord("these"),
            UnselectedWord("an"),
            UnselectedWord("sentence"),
            UnselectedWord("expensive"),
            UnselectedWord("beautiful"),
        ),
        sentenceUI = listOf(null, ", ", "I ", "went ", "to ", "the ", null,
            " and ", "bought ", "a ", "very ", "big ", null),
        onUnselectedWordClick = {},
        onSelectedWordClick = {}
    )
}