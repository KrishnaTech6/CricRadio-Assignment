package org.example.project.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchResponse(
    @SerialName("statusCode") val statusCode: Int,
    @SerialName("responseData") val responseData: Response,
    @SerialName("time") val time: String
)
@Serializable
data class Response(
    @SerialName("message") val message: String,
    @SerialName("result") val result: MatchResult
)
@Serializable
data class MatchResult(
    @SerialName("_id") val id: String,
    @SerialName("firstUmpire") val firstUmpire: String,
    @SerialName("key") val key: String,
    @SerialName("matchReferee") val matchReferee: String,
    @SerialName("related_name") val relatedName: String,
    @SerialName("season") val season: Season,
    @SerialName("secoundUmpire") val secondUmpire: String,
    @SerialName("start_date") val startDate: StartDate,
    @SerialName("status") val status: String,
    @SerialName("teams") val teams: MatchTeams,
    @SerialName("thirdUmpire") val thirdUmpire: String,
    @SerialName("toss") val toss: Toss,
    @SerialName("venue") val venue: String,
    @SerialName("venueDetails") val venueDetails: VenueDetails?,
    @SerialName("weather") val weather: Weather?,
    @SerialName("venueStats") val venueStats: VenueStats?
)
@Serializable
data class Season(
    @SerialName("key") val key: String,
    @SerialName("name") val name: String
)
@Serializable
data class StartDate(
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("iso") val iso: String,
    @SerialName("str") val str: String,
    @SerialName("sky_check_ts") val skyCheckTs: Long
)
@Serializable
data class Toss(
    @SerialName("won") val won: String,
    @SerialName("decision") val decision: String,
    @SerialName("str") val str: String
)
@Serializable
data class VenueDetails(
    @SerialName("_id") val id: String,
    @SerialName("knownAs") val knownAs: String,
    @SerialName("capacity") val capacity: Int,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("cricinfoId") val cricinfoId: Int,
    @SerialName("description") val description: String,
    @SerialName("ends1") val ends1: String,
    @SerialName("ends2") val ends2: String,
    @SerialName("floodLights") val floodLights: Int,
    @SerialName("homeTo") val homeTo: String,
    @SerialName("isDeleted") val isDeleted: String?,
    @SerialName("photo") val photo: String,
    @SerialName("status") val status: String?,
    @SerialName("timezone") val timezone: String,
    @SerialName("updatedAt") val updatedAt: String,
    @SerialName("venueLocation") val venueLocation: String,
    @SerialName("venueScraptitle") val venueScraptitle: String,
    @SerialName("venue_info") val venueInfo: VenueInfo
)
@Serializable
data class VenueInfo(
    @SerialName("name") val name: String,
    @SerialName("smallName") val smallName: String,
    @SerialName("longName") val longName: String,
    @SerialName("location") val location: String,
    @SerialName("town") val town: String
)
@Serializable
data class Weather(
    @SerialName("location") val location: String,
    @SerialName("condition") val condition: WeatherCondition,
    @SerialName("chance_of_rain") val chanceOfRain: Int,
    @SerialName("temp_c") val tempC: Double,
    @SerialName("last_updated") val lastUpdated: String
)
@Serializable
data class WeatherCondition(
    @SerialName("code") val code: Int,
    @SerialName("icon") val icon: String,
    @SerialName("text") val text: String
)
@Serializable
data class VenueStats(
    @SerialName("matchesPlayed") val matchesPlayed: Int,
    @SerialName("lowestDefended") val lowestDefended: Int,
    @SerialName("highestChased") val highestChased: Int,
    @SerialName("batFirstWins") val batFirstWins: Int,
    @SerialName("ballFirstWins") val ballFirstWins: Int,
    @SerialName("battingFirst") val battingFirst: BattingStats,
    @SerialName("battingSecond") val battingSecond: BattingStats
)
@Serializable
data class BattingStats(
    @SerialName("averageScore") val averageScore: Int,
    @SerialName("highestScore") val highestScore: Int,
    @SerialName("lowestScore") val lowestScore: Int,
    @SerialName("paceWickets") val paceWickets: Int,
    @SerialName("spinWickets") val spinWickets: Int
)
