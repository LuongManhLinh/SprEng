package com.example.spreng.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

fun TTS(context: Context, sentence: String) {
    var textToSpeech: TextToSpeech? = null
    textToSpeech = TextToSpeech(
        context
    ) {
        if (it == android.speech.tts.TextToSpeech.SUCCESS) {
//            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//            val volume = (volumeLevel * audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)).toInt()
//            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
            textToSpeech?.let { txtToSpeech ->
                txtToSpeech.language = Locale.US
                txtToSpeech.setSpeechRate(1.0f)
                txtToSpeech.speak(
                    sentence,
                    android.speech.tts.TextToSpeech.QUEUE_ADD,
                    null,
                    null
                )
            }
        }
    }
}