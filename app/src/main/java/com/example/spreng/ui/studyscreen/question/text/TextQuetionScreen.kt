package com.example.spreng.ui.studyscreen.question.text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.R

@Composable
fun QuestionText(
    modifier: Modifier = Modifier,
    questionContent: String
) {
    Box(
        modifier = modifier
            .padding(dimensionResource(R.dimen.small))
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small)))
            .border(
                width = dimensionResource(R.dimen.very_tiny),
                color = Color.Black,
                shape = RoundedCornerShape(dimensionResource(R.dimen.small))
            )
    ) {
        Text(
            text = questionContent,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.small))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionTextPreview() {
    QuestionText(questionContent = "This is a question")
}