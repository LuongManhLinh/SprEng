package com.example.spreng.ui.studyscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.spreng.R
import com.example.spreng.ui.custom.CustomRoundedBorderBox

@Composable
internal fun StudyBottomButton(
    modifier: Modifier = Modifier,
    text: String,
    onBottomButtonPressed: () -> Unit
) {
    CustomRoundedBorderBox(
        cornerRadius = dimensionResource(R.dimen.small_medium),
        modifier = modifier
            .width(300.dp)
            .clickable {
                onBottomButtonPressed()
            }
        ,
        contentWidthDp = 300.dp,
        containerColor = colorResource(R.color.study_button),
        borderColor = colorResource(R.color.study_button_border),
        bottomBorderWidth = dimensionResource(R.dimen.small)
    ) {
        Text(
            text = text,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(dimensionResource(R.dimen.small))
        )
    }
}