package com.example.spreng

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.spreng.ui.studyscreen.BaseStudyScreen
import com.example.spreng.ui.theme.SprEngTheme

class StudyActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SprEngTheme {
                BaseStudyScreen(
                    onCancelling = {
                        val mainActivityIntent = Intent(this, MainActivity::class.java)
                        startActivity(mainActivityIntent)
                    }
                ) {

                }
            }
        }
    }

}