package com.nims.mvvmretrofitandflow.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nims.mvvmretrofitandflow.data.MainRepository
import com.nims.mvvmretrofitandflow.data.Movie
import com.nims.mvvmretrofitandflow.data.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _uiState =
        MutableStateFlow(MainUiState(movies = emptyList(), event = MainUiEvent.None))
    val uiState = _uiState.asStateFlow()

    init {
        fetchAllMovies()
    }

    private fun fetchAllMovies() {
        viewModelScope.launch {
            mainRepository.getPopularMovies().collectLatest { movieResults ->
                when (movieResults) {
                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(movies = movieResults.data) }
                    }
                    is NetworkResult.Failure -> {
                        _uiState.update { it.copy(event = MainUiEvent.Error) }
                    }
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(event = MainUiEvent.Loading) }
                    }
                }
            }
        }
    }

    data class MainUiState(
        val movies: List<Movie>,
        val event: MainUiEvent
    )

    sealed class MainUiEvent {
        object None : MainUiEvent()
        object Loading : MainUiEvent()
        object Error : MainUiEvent()
    }
}