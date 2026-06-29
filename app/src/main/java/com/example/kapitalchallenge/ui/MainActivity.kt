package com.example.kapitalchallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.kapitalchallenge.architecture.presentation.view.home.DisneyCharactersListScreen
import com.example.kapitalchallenge.ui.theme.KapitalChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KapitalChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisneyCharactersListScreen(modifier = Modifier.padding(innerPadding).background(
                        Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0B1020),
                            Color(0xFF161B2C),
                            Color(0xFF0B1020)
                        )
                    )))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainActivity() {
    KapitalChallengeTheme {
        DisneyCharactersListScreen(modifier = Modifier)
    }
}