package com.example.spreng.ui.studyscreen.answer.multichoice

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
fun MultiChoiceScreen(
    modifier: Modifier = Modifier,
    choices: List<String>,
    selectedIndex: Int?,
    onChoiceClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         items(choices.size) { index ->
            MultiChoiceItem(
                choice = choices[index],
                onChoiceClick = {
                    onChoiceClick(index)
                },
                isSelected = index == selectedIndex
            )
        }
    }
}

@Composable
private fun MultiChoiceItem(
    modifier: Modifier = Modifier,
    choice: String,
    isSelected: Boolean,
    onChoiceClick: () -> Unit
) {
    CustomRoundedBorderBox(
        modifier = modifier
            .width(320.dp)
            .padding(
                top = if (isSelected) {
                    dimensionResource(R.dimen.medium)
                } else {
                    dimensionResource(R.dimen.small)
                }
            )
            .clickable(interactionSource = null, indication = null) {
                onChoiceClick()
            },
        cornerRadius = dimensionResource(R.dimen.large),
        containerColor = colorResource(R.color.word_holder_unselected),
        borderColor = colorResource(R.color.word_holder_selected),
        bottomBorderWidth = if (isSelected) {
            0.dp
        } else {
            dimensionResource(R.dimen.small)
        },
        contentWidthDp = 320.dp,
    ) {
        Text(
            text = choice,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp,
            fontWeight = if (isSelected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.small_medium))
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun MultiChoiceScreenPreview() {
    MultiChoiceScreen(
        modifier = Modifier.fillMaxSize(),
        choices = listOf(
            "This is a very long text to test if the text will wrap around or not",
            "Choice 2",
            "Choice 3"),
        selectedIndex = 1,
        onChoiceClick = {}
    )
}