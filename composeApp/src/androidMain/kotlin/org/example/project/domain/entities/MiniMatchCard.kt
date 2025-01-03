package org.example.project.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val statusCode: Int,
    val responseData: ResponseData
)

@Serializable
data class ResponseData(
    val message: String,
    val result: MiniMatchCard
)

@Serializable
data class MiniMatchCard(
    @SerialName("key") val key: String?,
    @SerialName("status") val status: String?,
    @SerialName("format") val format: String?,
    @SerialName("announcement1") val announcement1: String?,
    @SerialName("announcement2") val announcement2: String?,
    @SerialName("teams") val teams: MatchTeams?,
    @SerialName("now") val now: CurrentMatchStats?,
    @SerialName("lastCommentary") val lastCommentary: Commentary?,
    @SerialName("powerplay") val powerPlay: String?,
    @SerialName("powerplayOver") val powerPlayOver: Int?,
    @SerialName("currentBattingOrder") val currentBattingOrder: Int?,
    @SerialName("settingObj") val settingObj: MatchSetting?
)


@Serializable
data class MatchTeams(
    @SerialName("a") val teamA: TeamDetails,
    @SerialName("b") val teamB: TeamDetails
)

@Serializable
data class TeamDetails(
    @SerialName("name") val name: String,
    @SerialName("shortName") val shortName: String,
    @SerialName("logo") val logo: String,
    @SerialName("a_1_score") val firstInningScore: InningScore?=null,
    @SerialName("a_2_score") val secondInningScore: InningScore?=null,
    @SerialName("b_1_score") val firstInningScoreB: InningScore?=null,
    @SerialName("b_2_score") val secondInningScoreB: InningScore?=null
)

@Serializable
data class InningScore(
    @SerialName("runs") val runs: Int,
    @SerialName("overs") val overs: String?,
    @SerialName("wickets") val wickets: Int,
    @SerialName("declare") val declare: Boolean
)

@Serializable
data class CurrentMatchStats(
    @SerialName("run_rate") val runRate: String?,
    @SerialName("req_run_rate") val requiredRunRate: String?,
    @SerialName("sessionLeft") val sessionLeft: String?
)

@Serializable
data class MatchSetting(
    @SerialName("currentTeam") val currentTeam: String?,
    @SerialName("currentInning") val currentInning: Int?
)

@Serializable
data class Commentary(
    @SerialName("primaryText") val primaryText: String?,
    @SerialName("secondaryText") val secondaryText: String?,
    @SerialName("tertiaryText") val tertiaryText: String?,
    @SerialName("isDone") val isDone: Boolean
)
