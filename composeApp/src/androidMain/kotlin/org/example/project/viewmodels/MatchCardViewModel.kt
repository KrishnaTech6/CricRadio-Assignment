package org.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.ApiResponse
import org.example.project.domain.entities.MiniMatchCard
import org.example.project.domain.usecases.GetMiniMatchCardUseCase
import org.example.project.util.onError
import org.example.project.util.onSuccess
import util.NetworkError


sealed class MiniMatchCardState {
    object Loading : MiniMatchCardState()
    data class Success(val data: ApiResponse) : MiniMatchCardState()
    data class Error(val error: NetworkError) : MiniMatchCardState()
}

class MatchCardViewModel(
    private val getMiniMatchCardUseCase: GetMiniMatchCardUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MiniMatchCardState>(MiniMatchCardState.Loading)
    val state: StateFlow<MiniMatchCardState> = _state.asStateFlow()

    init {
        fetchMiniMatchCard()
    }

    fun fetchMiniMatchCard() {
        viewModelScope.launch {
            _state.value = MiniMatchCardState.Loading
            getMiniMatchCardUseCase()
                .onSuccess { miniMatchCard ->
                    _state.value = MiniMatchCardState.Success(miniMatchCard)
                }
                .onError { networkError ->
                    _state.value = MiniMatchCardState.Error(networkError)
                }
        }
    }
}

class MatchCardViewModelFactory(
    private val getMiniMatchCardUseCase: GetMiniMatchCardUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchCardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MatchCardViewModel(getMiniMatchCardUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}