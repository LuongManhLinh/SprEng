package com.example.spreng.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    topBorderWidth: Dp = 0.dp,
    bottomBorderWidth: Dp = 0.dp,
    startBorderWidth: Dp = 0.dp,
    endBorderWidth: Dp = 0.dp,
    borderColor: Color = Color.Black,
    containerColor: Color = Color.White,
    contentWidthDp: Dp? = null,
    contentHeightDp: Dp? = null,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = borderColor, RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = startBorderWidth,
                    top = topBorderWidth,
                    end = endBorderWidth,
                    bottom = bottomBorderWidth
                )
                .then(
                    if (contentWidthDp != null) {
                        Modifier.width(contentWidthDp - startBorderWidth - endBorderWidth)
                    } else {
                        Modifier
                    }
                )
                .then(
                    if (contentHeightDp != null) {
                        Modifier.height(contentHeightDp - topBorderWidth - bottomBorderWidth)
                    } else {
                        Modifier
                    }
                )

                .clip(RoundedCornerShape(cornerRadius))
                .background(containerColor)
            ,
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}



@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.fillMaxSize()) {
        CustomRoundedBorderBox(
            cornerRadius = 8.dp,
            bottomBorderWidth = 4.dp,
            borderColor = Color.Red,
            contentWidthDp = 500.dp,
            contentHeightDp = 300.dp
        ) {
            Text(
                text = "Hello World Haha",
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}



