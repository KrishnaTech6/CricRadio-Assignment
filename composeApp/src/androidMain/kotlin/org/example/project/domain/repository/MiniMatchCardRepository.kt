package org.example.project.domain.repository

import org.example.project.domain.entities.ApiResponse
import org.example.project.domain.entities.MatchResponse
import org.example.project.domain.entities.MatchResult
import org.example.project.domain.entities.MiniMatchCard
import org.example.project.domain.entities.Weather
import org.example.project.util.Result
import util.NetworkError

interface MiniMatchCardRepository {
    suspend fun getMiniMatchCard(): Result<ApiResponse, NetworkError>
    suspend fun getVenueInfo(): Result<MatchResponse, NetworkError>
}
