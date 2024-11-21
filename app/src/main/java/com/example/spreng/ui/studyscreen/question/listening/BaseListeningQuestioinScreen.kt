package com.example.spreng.ui.studyscreen.question.listening

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.spreng.text2speech.TTS
import com.example.spreng.text2speech.isSpeaking


@Composable
fun BaseListeningQuestionScreen(
    modifier: Modifier = Modifier,
    context: Context,
    sentence: String
){
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    Log.i("Volume", isSpeaking.toString())
                    TTS(context, sentence)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "volume"
                )
            }
            Text(
                "Điền những gì đã nghe được"
            )
        }
    }
}