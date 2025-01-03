package org.example.project

import android.R.attr.level
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.domain.usecases.GetMiniMatchCardUseCase
import org.example.project.domain.usecases.GetVenueInfoUseCase
import org.example.project.remote.MiniMatchCardApi
import org.example.project.remote.MiniMatchCardRepositoryImpl


val httpClient = HttpClient {
    install(Logging) {
        level = LogLevel.BODY
    }
    install(ContentNegotiation) {
        json(
            json = Json {
                ignoreUnknownKeys = true
            }
        )
    }
}

val miniMatchCardApi = MiniMatchCardApi(httpClient)
val miniMatchCardRepository = MiniMatchCardRepositoryImpl(miniMatchCardApi)
val getMiniMatchCardUseCase = GetMiniMatchCardUseCase(miniMatchCardRepository)
val getVenueInfoUseCase= GetVenueInfoUseCase(miniMatchCardRepository)