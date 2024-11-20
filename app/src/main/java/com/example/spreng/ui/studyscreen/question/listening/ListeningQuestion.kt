package com.example.spreng.ui.studyscreen.question.listening

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.lang.reflect.Modifier
import java.util.Locale

@Composable
fun BaseListeningQuestion() {
    val context = LocalContext.current
    var textToSpeech: TextToSpeech? = null
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = {
                textToSpeech = TextToSpeech(
                    context
                ) {
                    if (it == TextToSpeech.SUCCESS) {
//            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//            val volume = (volumeLevel * audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)).toInt()
//            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
                        textToSpeech?.let { txtToSpeech ->
                            txtToSpeech.language = Locale.US
                            txtToSpeech.setSpeechRate(1.0f)
                            txtToSpeech.speak(
                                "Hello everyone my name is alex stupid",
                                TextToSpeech.QUEUE_ADD,
                                null,
                                null
                            )
                        }
                    }
                }
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

@Preview(showBackground = true)
@Composable
fun Pre() {
    BaseListeningQuestion()
}