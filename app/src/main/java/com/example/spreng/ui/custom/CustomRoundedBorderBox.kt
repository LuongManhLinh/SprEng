package com.example.spreng.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomRoundedBorderBox(
    modifier: Modifier = Modifier,
    cornerRadius: Dp,
    topBorderWidth: Dp = 1.dp,
    bottomBorderWidth: Dp = 1.dp,
    startBorderWidth: Dp = 1.dp,
    endBorderWidth: Dp = 1.dp,
    borderColor: Color = Color.Black,
    containerColor: Color = Color.White,
    content: @Composable (Modifier) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = borderColor, RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = Alignment.Center
    ) {
        content(
            Modifier
                .padding(
                    start = startBorderWidth,
                    top = topBorderWidth,
                    end = endBorderWidth,
                    bottom = bottomBorderWidth
                )
                .clip(RoundedCornerShape(cornerRadius))
                .background(containerColor)
        )
    }
}



@Preview
@Composable
private fun Preview() {
    CustomRoundedBorderBox(
        cornerRadius = 8.dp,
        bottomBorderWidth = 4.dp,
        borderColor = Color.Red
    ) { contentModifier ->
        Text(
            text = "Hello World",
            fontSize = 20.sp,
            modifier = contentModifier.padding(16.dp)
        )
    }
}


