package org.example.project

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import cricradioassignmentapp.composeapp.generated.resources.Res
import cricradioassignmentapp.composeapp.generated.resources.compose_multiplatform
import org.example.project.domain.entities.MatchResult
import org.example.project.domain.usecases.GetVenueInfoUseCase
import org.example.project.viewmodels.MiniMatchCardState
import org.example.project.viewmodels.VenueInfoState
import org.example.project.viewmodels.VenueInfoState.Loading
import org.example.project.viewmodels.VenueInfoState.Success
import org.example.project.viewmodels.VenueInfoViewModel
import org.example.project.viewmodels.VenueInfoViewModelFactory
import org.jetbrains.compose.resources.painterResource

@Composable
fun VenueInfo(venueInfoUseCase: GetVenueInfoUseCase) {
    val factory = remember { VenueInfoViewModelFactory(venueInfoUseCase) }
    val viewModel: VenueInfoViewModel = viewModel(factory = factory)

    val state by viewModel.venueInfoState.collectAsState()

    when (state) {
        is Loading -> LoadingScreen()
        is Success -> {
            val venueInfo = (state as Success).data.responseData.result
            Log.d("TAG", "ScoreCard: $venueInfo")
            Column {
                Venue(matchResult = venueInfo)
                Spacer(Modifier.height(16.dp))
                UmpiresInfo(matchResult = venueInfo)
                Spacer(Modifier.height(16.dp))
                Weather(weather = venueInfo.weather)
                Spacer(Modifier.height(16.dp))
                VenueStats(venueStats = venueInfo.venueStats)
                Spacer(Modifier.height(16.dp))
            }
        }
        is VenueInfoState.Error -> ErrorScreen((state as VenueInfoState.Error).error)
    }

}

@Composable
fun Venue(modifier: Modifier = Modifier, matchResult: MatchResult) {
    Column(modifier = Modifier.fillMaxWidth()){
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .height(200.dp)
                .background(Color.White),
            model = matchResult.venueDetails?.photo,
            contentDescription = null,
            contentScale = Crop
        )
        Spacer(Modifier.height(8.dp))
        Text(matchResult.venue, color = textBlue, textDecoration = Underline)
        Spacer(Modifier.height(16.dp))
        Row{
            Text(matchResult.relatedName, color = textColorWhite )
            Spacer(Modifier.width(4.dp))
            Text(matchResult.season.name, color = textColorWhite)
        }
        Text(text= matchResult.startDate.str , color = textColorWhite)

        Spacer(Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .border(width = .5.dp, color = Color(0xff43474e), shape = RoundedCornerShape(10.dp))
                .background(color = backgroundGray)
                .padding(12.dp)
        ){
            Text(matchResult.toss.str, color = textYellow)
        }
    }

}