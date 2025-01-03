package org.example.project.remote

import org.example.project.domain.entities.ApiResponse
import org.example.project.domain.entities.MatchResponse
import org.example.project.domain.repository.MiniMatchCardRepository
import util.NetworkError
import org.example.project.util.Result


class MiniMatchCardRepositoryImpl(private val api: MiniMatchCardApi) : MiniMatchCardRepository {
    override suspend fun getMiniMatchCard(): Result<ApiResponse, NetworkError> {
        return api.getMiniMatchCard()
    }

    override suspend fun getVenueInfo(): Result<MatchResponse, NetworkError> {
        return api.getVenueInfo()
    }
}
