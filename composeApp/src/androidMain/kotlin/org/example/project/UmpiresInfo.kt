package org.example.project

import android.R.attr.label
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.domain.entities.MatchResult

@Composable
fun UmpiresInfo(modifier: Modifier = Modifier, matchResult: MatchResult) {
    Column{
        Text("Umpires", color= textColorWhite)
        Spacer(Modifier.height(16.dp))
        Box(Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = backgroundGray)
            .border(width = 1.dp, color = Color(0xff43474e), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
        ){
            Column {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LabelText(Modifier.weight(1f), umpire = matchResult.firstUmpire)
                    LabelText(Modifier.weight(1f), umpire = matchResult.secondUmpire )
                }
                Spacer(Modifier.height(16.dp))
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.12f)))
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LabelText(Modifier.weight(1f), umpire = matchResult.thirdUmpire, label = "Third/TV Umpire")
                    LabelText(Modifier.weight(1f), umpire = matchResult.matchReferee, label= "Referee")
                }
            }
        }
    }

}

@Composable
fun LabelText(modifier: Modifier = Modifier, umpire: String, label:String = "Umpire") {
    Column(modifier = modifier){
        Text(label, fontSize = 11.sp, color = Color(0x61ffffff) )
        Spacer(Modifier.height(8.dp))
        Text(umpire, color = Color(0xDEffffff) )
    }
}