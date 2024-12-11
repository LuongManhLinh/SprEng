package com.example.spreng.ui.studyscreen.question.listening

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreng.R
import com.example.spreng.text2speech.TTS
import com.example.spreng.text2speech.isSpeaking
import com.example.spreng.ui.custom.CustomRoundedBorderBox
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun BaseListeningQuestionScreen(
    modifier: Modifier = Modifier,
    context: Context,
    sentence: String,
) {
    var isPressed by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .width(240.dp)
            .height(64.dp),
        contentAlignment = Alignment.Center
    ) {
        CustomRoundedBorderBox(
            modifier = Modifier
                .padding(top = if (isPressed) {
                     4.dp
                } else {
                    0.dp
                }),
            cornerRadius = dimensionResource(R.dimen.medium),
            bottomBorderWidth = if(isPressed) 0.dp else 4.dp,
            borderColor = Color(108, 126, 185),
            containerColor = Color(198, 215, 235),
            contentWidthDp = 240.dp,
            contentHeightDp = 64.dp
        )
        {
            Button(
                onClick = {
                    isPressed = true;
                    coroutineScope.launch {
                        delay(125)
                        isPressed = false
//                        Log.i("Volume", isSpeaking.toString())
                        TTS(context, sentence)
                    }
                },
                modifier = modifier,
                shape = RoundedCornerShape(dimensionResource(R.dimen.medium)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(198, 215, 235))
            ) {
                Image(
                    painter = painterResource(R.drawable.volume),
                    contentDescription = "volume",
                    modifier = Modifier
                        .padding(end = dimensionResource(R.dimen.small))
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Nhấn để nghe",
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Testing() {
    BaseListeningQuestionScreen(context = LocalContext.current, sentence = "hello")
}