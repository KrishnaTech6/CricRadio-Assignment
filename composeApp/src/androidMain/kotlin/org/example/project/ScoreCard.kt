package org.example.project

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import org.example.project.domain.entities.MiniMatchCard
import util.NetworkError
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import org.example.project.domain.entities.TeamDetails
import org.example.project.domain.usecases.GetMiniMatchCardUseCase
import org.example.project.viewmodels.MatchCardViewModel
import org.example.project.viewmodels.MatchCardViewModelFactory
import org.example.project.viewmodels.MiniMatchCardState
import org.example.project.viewmodels.MiniMatchCardState.Loading
import org.example.project.viewmodels.MiniMatchCardState.Success

@Composable
fun ScoreCard(
    modifier: Modifier = Modifier,
    getMiniMatchCardUseCase: GetMiniMatchCardUseCase
) {
    val factory = remember { MatchCardViewModelFactory(getMiniMatchCardUseCase) }
    val viewModel: MatchCardViewModel = viewModel(factory = factory)

    val state by viewModel.state.collectAsState()

    when (state) {
        is Loading -> LoadingScreen()
        is Success -> {
            val matchCard = (state as Success).data.responseData.result
            Log.d("TAG", "ScoreCard: $matchCard")

            MatchCardContent(matchCard = matchCard, modifier = modifier)
        }
        is MiniMatchCardState.Error -> ErrorScreen((state as MiniMatchCardState.Error).error)
    }
}

@Composable
fun MatchCardContent(
    matchCard: MiniMatchCard,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
            .fillMaxWidth()
            .background(blueBackground)
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val isBattingA = matchCard.settingObj?.currentTeam == "a"
                val isBattingB = matchCard.settingObj?.currentTeam == "b"
                Log.d("TAG", "MatchCardContent: $isBattingA, $isBattingB")
                TeamA(team = matchCard.teams!!.teamA, isBatting = isBattingA)
                Text(
                    matchCard.lastCommentary?.primaryText ?: "",
                    color = Color.White,
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                )
                TeamB(team = matchCard.teams.teamB, isBatting = isBattingB)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .height(1.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CRR: ${matchCard.now?.runRate ?: "N/A"}",
                    color = Color.White,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "RRR: ${matchCard.now?.requiredRunRate ?:"N/A"}",
                    color = Color.White,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = matchCard.announcement1 ?: "",
                    color = Color.White,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun TeamA(team: TeamDetails?, isBatting:Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = team?.logo,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = team?.shortName?:"",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (isBatting)
            Image(painter = painterResource(R.drawable.bat_icon), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${team?.firstInningScore?.runs ?: 0} / ${team?.firstInningScore?.wickets ?: 0}",
            color = if (isBatting) textYellow else Color.White,
            style = if (isBatting) MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ) else MaterialTheme.typography.body2,
        )
        val overs = team?.firstInningScore?.overs ?: "0.0"
        Text(
            text = "$overs overs",
            color = Color.White,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun TeamB(team: TeamDetails?, isBatting: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = team?.logo,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = team?.shortName?:"",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2
            )
            Spacer(Modifier.width(8.dp))
            if (isBatting)
                Image(painter = painterResource(R.drawable.bat_icon), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${team?.firstInningScoreB?.runs ?: 0} / ${team?.firstInningScoreB?.wickets ?: 0}",
            color = if (isBatting) textYellow else Color.White,
            style = if (isBatting) MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ) else MaterialTheme.typography.body2,
        )
        Text(
            text = "${team?.firstInningScoreB?.overs?: "0.0"} overs",
            color = Color.White,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun ErrorScreen(error: NetworkError) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (error) {
                NetworkError.REQUEST_TIMEOUT -> "Request Timed Out"
                NetworkError.NO_INTERNET -> "No Internet Connection"
                NetworkError.SERVER_ERROR -> "Server Error"
                else -> "An Unknown Error Occurred"
            },
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.error
        )
    }
}