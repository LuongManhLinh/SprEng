package com.example.spreng.ui.studyscreen.answer.writing

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
fun BaseWritingScreen(
    modifier: Modifier = Modifier,
    inputAnswer: String,
    saveInputAnswer: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.small))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small)))
            .border(
                width = 1.dp,
                color = Color(108, 126, 225),
                shape = RoundedCornerShape(dimensionResource(R.dimen.small))
            )
    ) {
        CustomRoundedBorderBox(
            modifier = modifier,
            cornerRadius = 8.dp,
            borderColor = Color(108, 126, 225),
            containerColor = Color(198, 215, 235),
            bottomBorderWidth = 4.dp
        ) {
            TextField(
                value = inputAnswer,
                onValueChange = { saveInputAnswer(it) },
                textStyle = MaterialTheme.typography.titleLarge,
                modifier = modifier.fillMaxSize()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BasePre() {
    BaseWritingScreen(
        inputAnswer = "Hello",
        saveInputAnswer = {}
    )
}
