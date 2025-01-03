package org.example.project

import android.R.attr.visible
import android.R.id.text1
import android.R.id.text2
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.domain.entities.VenueStats
import kotlin.math.sin

@Composable
fun VenueStats(modifier: Modifier = Modifier, venueStats: VenueStats?) {
    Column {
        Text("Venue Stats", color = textColorWhite)
        Spacer(Modifier.height(16.dp))
        Box(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xff080808),
                            Color(0xff080808),
                            Color.White.copy(alpha = 0.05f)
                        )
                    )
                )
                .border(width = 1.dp, color = Color(0xff43474e), shape = RoundedCornerShape(12.dp))
                .padding(vertical = 16.dp)
        ) {
            Column {
                Stats(title = "Matches Played", value = venueStats?.matchesPlayed.toString())
                Stats(title = "Lowest Defended", value = venueStats?.lowestDefended.toString())
                Stats(title = "Highest Chased", value = venueStats?.highestChased.toString())
                Stats(title = "Win Bat First", value = venueStats?.batFirstWins.toString())
                Stats(title = "Win Ball First", value = venueStats?.ballFirstWins.toString(), isDiv = false)

                Label(x0 = "1st Inn",x1=  "2nd Inn")

                StatsScoreRow(
                    text1 = "Avg Score",
                    text2 = venueStats?.battingFirst?.averageScore.toString(),
                    text3 = venueStats?.battingSecond?.averageScore.toString()
                )

                StatsScoreRow(
                    text1 = "Lowest Score",
                    text2 = venueStats?.battingFirst?.lowestScore.toString(),
                    text3 = venueStats?.battingSecond?.lowestScore.toString()
                )

                StatsScoreRow(
                    text1 = "Spin Wickets",
                    text2 = venueStats?.battingFirst?.spinWickets.toString(),
                    text3 = venueStats?.battingSecond?.spinWickets.toString(),
                )
                StatsScoreRow(
                    text1 = "Pace Wickets",
                    text2 = venueStats?.battingFirst?.paceWickets.toString(),
                    text3 = venueStats?.battingSecond?.paceWickets.toString(),
                    isDiv = false
                )
            }
        }
    }
}

@Composable
fun Label(modifier: Modifier= Modifier, x0: String, x1: String) {
    Column {
        Spacer(Modifier.height(8.dp))
        Row(
            Modifier
                .background(color = backgroundGray)
                .padding(8.dp)
        ) {
            Text("", color = textColorWhite, modifier = modifier.weight(0.6f))
            Text(x0, color = textColorWhite, modifier = modifier.weight(0.2f))
            Text(x1, color = textColorWhite, modifier = modifier.weight(0.2f))
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun Stats(modifier: Modifier = Modifier, title: String, value: String, isDiv: Boolean= true) {
    Row(Modifier.padding(horizontal = 16.dp)){
        Text(title, color = textColorWhite, modifier = modifier.weight(.8f))
        Text(value, color = Color.White, modifier = modifier.weight(.2f))
    }
    if(isDiv){
        Divider(
            color = textColorWhite,
            thickness = 0.5.dp,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }

}

@Composable
fun StatsScoreRow(modifier: Modifier = Modifier, text1: String, text2: String, text3: String, isDiv:Boolean = true) {
    Row(Modifier.padding(horizontal = 16.dp)){
        Text(text1, color = textColorWhite, modifier = modifier.weight(0.6f))
        Text(text2, color = Color.White, modifier = modifier.weight(0.2f))
        Text(text3, color = Color.White, modifier = modifier.weight(0.2f))
    }
    if(isDiv){
        Divider(
            color = textColorWhite,
            thickness = 0.5.dp,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}

