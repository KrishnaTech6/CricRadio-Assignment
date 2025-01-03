package org.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.entities.MatchResponse
import org.example.project.domain.entities.MatchResult
import org.example.project.domain.usecases.GetVenueInfoUseCase
import org.example.project.util.onError
import org.example.project.util.onSuccess
import util.NetworkError

sealed class VenueInfoState {
    object Loading : VenueInfoState()
    data class Success(val data: MatchResponse) : VenueInfoState()
    data class Error(val error: NetworkError) : VenueInfoState()
}

class VenueInfoViewModel(
    private val getVenueInfoUseCase: GetVenueInfoUseCase
) : ViewModel() {

    private val _venueInfoState = MutableStateFlow<VenueInfoState>(VenueInfoState.Loading)
    val venueInfoState: StateFlow<VenueInfoState> = _venueInfoState.asStateFlow()

    init {
        fetchVenueInfo()
    }

    fun fetchVenueInfo() {
        viewModelScope.launch {
            _venueInfoState.value = VenueInfoState.Loading
            getVenueInfoUseCase()
                .onSuccess { matchResult ->
                    _venueInfoState.value = VenueInfoState.Success(matchResult)
                }
                .onError { networkError ->
                    _venueInfoState.value = VenueInfoState.Error(networkError)
                }
        }
    }
}

class VenueInfoViewModelFactory(
    private val getVenueInfoUseCase: GetVenueInfoUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VenueInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VenueInfoViewModel(getVenueInfoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}