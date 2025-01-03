package org.example.project


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun App() {
    MaterialTheme {
        Column(Modifier.background(Color.Black).padding(horizontal = 16.dp).verticalScroll(rememberScrollState())){
            ScoreCard(getMiniMatchCardUseCase = getMiniMatchCardUseCase)
            Spacer(Modifier.height(16.dp))
            VenueInfo(venueInfoUseCase = getVenueInfoUseCase)
        }
    }
}