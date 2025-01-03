package org.example.project.domain.usecases

import org.example.project.domain.entities.ApiResponse
import org.example.project.domain.entities.MatchResult
import org.example.project.domain.entities.MiniMatchCard
import org.example.project.domain.repository.MiniMatchCardRepository
import org.example.project.util.Result
import util.NetworkError


class GetMiniMatchCardUseCase(private val repository: MiniMatchCardRepository) {
    suspend operator fun invoke(): Result<ApiResponse, NetworkError> = repository.getMiniMatchCard()
}
