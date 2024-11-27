package com.example.spreng.ui.studyscreen.question.listening

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spreng.R
import com.example.spreng.text2speech.TTS
import com.example.spreng.text2speech.isSpeaking
import com.example.spreng.ui.custom.CustomRoundedBorderBox


@Composable
fun BaseListeningQuestionScreen(
    modifier: Modifier = Modifier,
    context: Context,
    sentence: String,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CustomRoundedBorderBox(
            cornerRadius = 8.dp,
            bottomBorderWidth = 4.dp,
            borderColor = Color(108, 126, 225),
            containerColor = Color(198, 215, 235)
        )
        {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.volume),
                    contentDescription = "volume",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable(
                            onClick = {
                                Log.i("Volume", isSpeaking.toString())
                                TTS(context, sentence)
                            }
                        ),
                    contentScale = ContentScale.Crop
                )
//            IconButton(
//                onClick = {
//                    Log.i("Volume", isSpeaking.toString())
//                    TTS(context, sentence)
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Call,
//                    contentDescription = "volume"
//                )
//            }
                Text(
                    "Điền những gì đã nghe được",
                    modifier = Modifier.padding(8.dp)
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