package com.example.spreng.ui.studyscreen.answer.wordpicker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.spreng.R
import com.example.spreng.ui.studyscreen.SelectedWord
import com.example.spreng.ui.studyscreen.UnselectedWord
import kotlin.math.roundToInt


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordPickerSequenceScreen(
    modifier: Modifier = Modifier,
    unselectedWords: List<UnselectedWord>,
    selectedWords: List<SelectedWord>,
    onUnselectedWordClick: (Int) -> Unit,
    onSelectedWordClick: (SelectedWord) -> Unit
) {
    var cardMaxHeight by remember { mutableStateOf(0.dp) }
    var pickerMaxHeight by remember { mutableStateOf(0.dp) }

    BaseWordPickerScreen(
        modifier = modifier,
        unselectedWords = unselectedWords,
        onUnselectedWordClick = onUnselectedWordClick,
        getCardSize = { _, height ->
            if (cardMaxHeight < height) {
                cardMaxHeight = height
            }
        },
        getPickerMaxHeight = {
            if (pickerMaxHeight < it) {
                pickerMaxHeight = it
            }
        }

    ) { contentModifier ->
        Box(
            modifier = contentModifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.small))
                .drawBehind {
                    drawLinedBackground(
                        offsetY = cardMaxHeight,
                        lineSpacing = cardMaxHeight,
                        lineColor = Color.Gray,
                        numberLine = (pickerMaxHeight.value / cardMaxHeight.value).roundToInt()
                    )
                }
        ) {
            FlowRow {
                selectedWords.forEach { word ->
                    WordItem(
                        word = word.word,
                        onClick = { onSelectedWordClick(word) }
                    )
                }
            }
        }
    }

}

fun DrawScope.drawLinedBackground(
    offsetY: Dp,
    lineSpacing: Dp,
    lineColor: Color,
    numberLine: Int
) {
    val lineSpacingPx = lineSpacing.toPx()
    var currentY = offsetY.toPx()
    for (i in 0 until numberLine) {
        drawLine(
            color = lineColor,
            start = androidx.compose.ui.geometry.Offset(0f, currentY),
            end = androidx.compose.ui.geometry.Offset(size.width, currentY),
            strokeWidth = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(8.dp.toPx(), 4.dp.toPx()),
                phase = 0f
            )
        )
        currentY += lineSpacingPx
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