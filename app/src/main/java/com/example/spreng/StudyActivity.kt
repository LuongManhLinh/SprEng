package com.example.spreng

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.spreng.ui.studyscreen.StudyFlowScreen
import com.example.spreng.ui.theme.SprEngTheme

class StudyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SprEngTheme {
                StudyFlowScreen()
            }
        }
    }

}