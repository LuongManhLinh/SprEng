package com.example.spreng.ui.studyscreen.answer.wordpicker

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import com.example.spreng.ui.studyscreen.UnselectedWord

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BaseWordPickerScreen(
    modifier: Modifier = Modifier,
    unselectedWords: List<UnselectedWord>,
    onUnselectedWordClick: (Int) -> Unit,
    getCardSize: (Dp, Dp) -> Unit = { _, _ -> },
    getPickerMaxHeight: (Dp) -> Unit = { },
    content: @Composable (Modifier) -> Unit
) {
    val currentDensity = LocalDensity.current

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        content(Modifier.weight(1f))

        FlowRow (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .onGloballyPositioned {
                    with(currentDensity) {
                        getPickerMaxHeight(it.size.height.toDp())
                    }
                }
        ){
            for (idx in unselectedWords.indices) {
                val unselectedWord = unselectedWords[idx]
                if (unselectedWord.selected) {
                    WordHolder(
                        wordHolder = unselectedWord.word,
                        textAlpha = 0f
                    )
                } else {
                    WordItem(
                        word = unselectedWord.word,
                        onClick = {
                            onUnselectedWordClick(idx)
                        },
                        modifier = Modifier.onGloballyPositioned {
                            with(currentDensity) {
                                getCardSize(
                                    it.size.width.toDp(),
                                    it.size.height.toDp()
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun WordItem(
    modifier: Modifier = Modifier,
    word: String,
    widthDp: Dp? = null,
    onClick: () -> Unit = {}
) {
    WordHolder(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = null
            ) {
                onClick()
            },
        wordHolder = word,
        widthDp = widthDp
    )

}

@Composable
private fun WordHolder(
    modifier: Modifier = Modifier,
    wordHolder: String,
    textAlpha: Float = 1f,
    widthDp: Dp? = null,
) {
    CustomRoundedBorderBox(
        cornerRadius = dimensionResource(R.dimen.small_medium),
        bottomBorderWidth = dimensionResource(R.dimen.tiny),
        borderColor = colorResource(R.color.word_holder_selected),
        containerColor = if (textAlpha == 1f) {
            colorResource(R.color.word_holder_unselected)
        } else {
            colorResource(R.color.word_holder_selected)
        },
        modifier = modifier.padding(dimensionResource(R.dimen.small)),
        contentWidthDp = widthDp
    ) {
        Text(
            text = wordHolder,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.small))
                .alpha(textAlpha)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseWordPickerScreenPreview() {
    BaseWordPickerScreen(
        unselectedWords = listOf(
            UnselectedWord("Hello", true),
            UnselectedWord("World", false),
            UnselectedWord("This", false),
            UnselectedWord("Is", false),
            UnselectedWord("A", false),
            UnselectedWord("Test", false),
            UnselectedWord("For", false),
            UnselectedWord("Word", false),
            UnselectedWord("Picker", false),
            UnselectedWord("Screen", false),
        ),
        onUnselectedWordClick = {},
        content = {
            Box(
                modifier = it.border(1.dp, Color.Black, RoundedCornerShape(8.dp)).fillMaxSize()
            )
        }
    )
}