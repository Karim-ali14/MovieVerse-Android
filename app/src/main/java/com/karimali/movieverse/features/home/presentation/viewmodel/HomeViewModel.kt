package com.karimali.movieverse.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimali.movieverse.core.presentation.UiState
import com.karimali.movieverse.features.home.domain.entities.HomeData
import com.karimali.movieverse.features.home.domain.usecases.FetchNowPlayingMoviesUseCase
import com.karimali.movieverse.features.home.domain.usecases.FetchNowPopularMoviesUseCase
import com.karimali.movieverse.features.home.domain.usecases.FetchNowTopRatedMoviesUseCase
import com.karimali.movieverse.features.home.domain.usecases.FetchNowUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNowUpcomingMoviesUseCase: FetchNowUpcomingMoviesUseCase,
    private val fetchNowPopularMoviesUseCase: FetchNowPopularMoviesUseCase,
    private val fetchNowTopRatedMoviesUseCase: FetchNowTopRatedMoviesUseCase,
    private val fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase
) : ViewModel() {
    private val _homeDataState: MutableStateFlow<UiState<HomeData>> =
        MutableStateFlow(UiState.Initial)
    val homeDataState : StateFlow<UiState<HomeData>> = _homeDataState.asStateFlow()
    private var fetchDataJob: Job? = null

    init {
        fetchHomeData()
    }

    fun fetchHomeData() {
        println("enter to Scope sdf $fetchDataJob")
        fetchDataJob?.cancel()
        fetchDataJob = viewModelScope.launch {
            _homeDataState.emit(UiState.Loading)
            try {
                val homeData = coroutineScope {
                    val topRated = async { fetchNowTopRatedMoviesUseCase() }
                    val popular = async { fetchNowPopularMoviesUseCase() }
                    val upcoming = async { fetchNowUpcomingMoviesUseCase() }
                    val nowPlaying = async { fetchNowPlayingMoviesUseCase() }

                    HomeData(
                        topRatedMovie = topRated.await().movies,
                        nowUpcomingMovie = upcoming.await().movies,
                        nowPlayingMovie = nowPlaying.await().movies,
                        nowPopularMovie = popular.await().movies,
                    )
                }
                _homeDataState.emit( UiState.Success(homeData))
            } catch (e: CancellationException) {
                println("enter to Scope cancellation ${e.localizedMessage}")
                throw e
            } catch (e: Exception) {
                _homeDataState.emit(UiState.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                ))
            }

        }
    }
}