package org.example.project.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import org.example.project.domain.entities.ApiResponse
import org.example.project.domain.entities.MatchResponse
import org.example.project.util.Constants
import org.example.project.util.Constants.authorization
import org.example.project.util.Constants.baseUrl
import org.example.project.util.Result
import util.NetworkError

class MiniMatchCardApi(private val client: HttpClient) {
    suspend fun getMiniMatchCard(): Result<ApiResponse, NetworkError> {
        return getRequest("mini-match-card", ApiResponse::class.java)
    }

    suspend fun getVenueInfo(): Result<MatchResponse, NetworkError> {
        return getRequest("venue-info", MatchResponse::class.java)
    }

    private suspend inline fun <reified T> getRequest(
        endpoint: String,
        responseClass: Class<T>,
    ): Result<T, NetworkError> {
        val response = client.get("$baseUrl$endpoint?key=${Constants.apiKey}") {
            header("Authorization", authorization)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val body = response.body<T>()
                Result.Success(body)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

}