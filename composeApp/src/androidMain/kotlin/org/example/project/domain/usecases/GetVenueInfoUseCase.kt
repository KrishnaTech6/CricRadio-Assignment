package org.example.project.domain.usecases

import org.example.project.domain.entities.MatchResponse
import org.example.project.domain.entities.MatchResult
import org.example.project.domain.entities.MiniMatchCard
import org.example.project.domain.repository.MiniMatchCardRepository
import org.example.project.util.Result
import util.NetworkError

class GetVenueInfoUseCase(private val repository: MiniMatchCardRepository) {
    suspend operator fun invoke(): Result<MatchResponse, NetworkError> = repository.getVenueInfo()
}