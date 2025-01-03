package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.example.project.domain.entities.Weather

@Composable
fun Weather(modifier: Modifier = Modifier, weather: Weather?) {
    Column {
        Text("Weather", color = textColorWhite)
        Spacer(Modifier.height(16.dp))
        Box(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = backgroundGray)
                .border(width = 1.dp, color = Color(0xff43474e), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                AsyncImage(
                    modifier = modifier.size(60.dp),
                    model = weather?.condition?.icon,
                    contentDescription = null
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(weather?.location?:"", color = textColorWhite)
                    Text("${weather?.tempC}° C", color = textYellow, fontSize = 22.sp)
                    Text(weather?.condition?.text?:"", fontSize = 16.sp, color = textBlue)
                }
                Spacer(
                    Modifier
                        .background(Color.White)
                        .width(1.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Last Updated", color = textColorWhite)
                    Text(weather?.lastUpdated?:"", color = Color.White)
                }
            }

        }
    }

}