package com.example.spreng.ui.studyscreen.answer.wordpicker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.spreng.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BaseWordPickerScreen(
    modifier: Modifier = Modifier,
    unselectedWords: List<UnselectedWord>,
    onUnselectedWordClick: (Int) -> Unit,
    setCardSize: (Dp, Dp) -> Unit = {_, _ -> },
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
                                setCardSize(
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
        borderStroke = BorderStroke(1.dp, Color.Gray)
    )

}

@Composable
fun WordHolder(
    modifier: Modifier = Modifier,
    wordHolder: String,
    textAlpha: Float = 1f,
    borderStroke: BorderStroke? = null,
) {
    Box(
        modifier = modifier
            .padding(dimensionResource(R.dimen.tiny))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small_medium)))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(
                borderStroke?: BorderStroke(0.dp, Color.Transparent),
                shape = RoundedCornerShape(dimensionResource(R.dimen.small_medium))
            )

        ,
        contentAlignment = Alignment.Center
    ) {
       Text(
           text = wordHolder,
           style = MaterialTheme.typography.bodyLarge,
           modifier = Modifier
               .padding(dimensionResource(R.dimen.small))
               .alpha(textAlpha)
       )
    }
}