package com.karimali.movieverse.features.home.presentation.viewmodel

import app.cash.turbine.test
import com.karimali.movieverse.core.presentation.UiState
import com.karimali.movieverse.features.home.domain.entities.HomeData
import com.karimali.movieverse.features.home.domain.entities.MovieEntity
import com.karimali.movieverse.features.home.domain.entities.MoviesResponseEntity
import com.karimali.movieverse.features.home.domain.usecases.FetchNowPlayingMoviesUseCase
import com.karimali.movieverse.features.home.domain.usecases.FetchNowPopularMoviesUseCase
import com.karimali.movieverse.features.home.domain.usecases.FetchNowTopRatedMoviesUseCase
import com.karimali.movieverse.features.home.domain.usecases.FetchNowUpcomingMoviesUseCase
import com.karimali.movieverse.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private lateinit var fetchNowUpcomingMoviesUseCase: FetchNowUpcomingMoviesUseCase
    private lateinit var fetchNowPopularMoviesUseCase: FetchNowPopularMoviesUseCase
    private lateinit var fetchNowTopRatedMoviesUseCase: FetchNowTopRatedMoviesUseCase
    private lateinit var fetchNowPlayingMoviesUseCase: FetchNowPlayingMoviesUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        fetchNowPlayingMoviesUseCase = mockk()
        fetchNowTopRatedMoviesUseCase = mockk()
        fetchNowUpcomingMoviesUseCase = mockk()
        fetchNowPopularMoviesUseCase = mockk()
    }

    private fun mockAllUseCasesToSucceed() {
        coEvery { fetchNowPopularMoviesUseCase() } returns MoviesDummyData.moviesResponse
        coEvery { fetchNowPlayingMoviesUseCase() } returns MoviesDummyData.moviesResponse
        coEvery { fetchNowUpcomingMoviesUseCase() } returns MoviesDummyData.moviesResponse
        coEvery { fetchNowTopRatedMoviesUseCase() } returns MoviesDummyData.moviesResponse
    }

    private fun createHomeViewModel(): HomeViewModel {
        return HomeViewModel(
            fetchNowPlayingMoviesUseCase = fetchNowPlayingMoviesUseCase,
            fetchNowUpcomingMoviesUseCase = fetchNowUpcomingMoviesUseCase,
            fetchNowTopRatedMoviesUseCase = fetchNowTopRatedMoviesUseCase,
            fetchNowPopularMoviesUseCase = fetchNowPopularMoviesUseCase,
        )
    }

    @Test
    fun `fetch four movies useCases then first emitting loading then all useCases success`() =
        runTest(mainDispatcherRule.testDispatcher) {
            // Arrange
            mockAllUseCasesToSucceed()

            // Act
            val viewmodel = createHomeViewModel()

            // Asset
            viewmodel.homeDataState.test {
                assertEquals(UiState.Initial, awaitItem())
                assertEquals(UiState.Loading, awaitItem())

                val actualState = awaitItem()

                assertTrue(actualState is UiState.Success)

                val actualData = (actualState as UiState.Success).data

                assertEquals(
                    MoviesDummyData.homeData,
                    actualData
                )
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `fetch four movies useCases then first emitting loading then any useCase fails without message`() =
        runTest(mainDispatcherRule.testDispatcher) {
            // Arrange
            mockAllUseCasesToSucceed()
            coEvery { fetchNowPopularMoviesUseCase() } throws RuntimeException()

            // Act
            val viewmodel = createHomeViewModel()

            // Asset
            viewmodel.homeDataState.test {
                assertEquals(UiState.Initial, awaitItem())
                assertEquals(UiState.Loading, awaitItem())
                assertEquals(
                    "An unexpected error occurred",
                    (awaitItem() as UiState.Error).message
                )
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `fetch four movies useCases then first emitting loading then any useCase fails with message`() =
        runTest(mainDispatcherRule.testDispatcher) {
            // Arrange
            mockAllUseCasesToSucceed()
            coEvery { fetchNowPopularMoviesUseCase() } throws RuntimeException("Io Exception")


            // Act
            val viewmodel = createHomeViewModel()

            // Asset
            viewmodel.homeDataState.test {
                assertEquals(UiState.Initial, awaitItem())
                assertEquals(UiState.Loading, awaitItem())
                assertEquals(
                    "Io Exception",
                    (awaitItem() as UiState.Error).message
                )
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `all use cases are invoked exactly once on initialization`() = runTest(mainDispatcherRule.testDispatcher) {
        // Arrange
        mockAllUseCasesToSucceed()

        // Act
        createHomeViewModel()
        advanceUntilIdle()

        // Assert
        coVerify(exactly = 1) { fetchNowTopRatedMoviesUseCase() }
        coVerify(exactly = 1) { fetchNowPlayingMoviesUseCase() }
        coVerify(exactly = 1) { fetchNowPopularMoviesUseCase() }
        coVerify(exactly = 1) { fetchNowUpcomingMoviesUseCase() }

    }

    // This test protects the CancellationException handling:
    // if you remove `catch (e: CancellationException) { throw e }` from the ViewModel, it fails
    @Test
    fun `cancelling an in-flight fetch never emits a false Error`() =
        runTest(mainDispatcherRule.testDispatcher) {
            // Arrange: the first call is slow so the init job is suspended when it gets cancelled
            var calls = 0
            mockAllUseCasesToSucceed()
            coEvery { fetchNowPopularMoviesUseCase() } coAnswers {
                // Only the FIRST call (from init) is slowed down.
                // Without this delay the use case returns instantly, the whole job finishes
                // on its own, and there is nothing left to cancel when we call fetchHomeData() again.
                // With the delay, the first job stays suspended (still running) so we can
                // cancel it in the middle of its work.
                if (++calls == 1) delay(1_000)
                MoviesDummyData.moviesResponse
            }

            // Act
            val viewModel = createHomeViewModel()

            // Assert
            viewModel.homeDataState.test {
                assertEquals(UiState.Initial, awaitItem())
                assertEquals(UiState.Loading, awaitItem()) // the first job started and is suspended

                viewModel.fetchHomeData() // cancels the first job while it is running

                // The second Loading is ignored (StateFlow conflation), so Success comes next
                assertTrue(awaitItem() is UiState.Success) // no Error in between
                cancelAndIgnoreRemainingEvents()
            }
        }
}

object MoviesDummyData {

    val movies = listOf(
        MovieEntity(
            id = 1,
            title = "Inception",
            overview = "A thief who steals corporate secrets through dream-sharing technology.",
            posterUrl = "/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg",
            backdropUrl = "/s3TBrRGB1iav7gFOCNx3H31MoES.jpg",
            releaseDate = "2010-07-16",
            rating = 8.4,
            voteCount = 35000,
            genres = listOf(28, 878, 12),
            popularity = 85.5,
            isAdult = false
        ),
        MovieEntity(
            id = 2,
            title = "Interstellar",
            overview = "A team of explorers travels beyond this galaxy to discover humanity's future.",
            posterUrl = "/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
            backdropUrl = "/xJHokMbljvjADYdit5fK5VQsXEG.jpg",
            releaseDate = "2014-11-07",
            rating = 8.7,
            voteCount = 36000,
            genres = listOf(12, 18, 878),
            popularity = 95.2,
            isAdult = false
        ),
        MovieEntity(
            id = 3,
            title = "The Dark Knight",
            overview = "Batman faces the Joker, a criminal mastermind who plunges Gotham into chaos.",
            posterUrl = "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            backdropUrl = "/hkBaDkMWbLaf8B1lsWsKX7Ew3Xq.jpg",
            releaseDate = "2008-07-18",
            rating = 9.0,
            voteCount = 34000,
            genres = listOf(18, 28, 80, 53),
            popularity = 90.3,
            isAdult = false
        )
    )

    val moviesResponse = MoviesResponseEntity(
        page = 1,
        movies = movies,
        totalPages = 10,
        totalResults = movies.size,
        dates = null
    )

    val homeData = HomeData(
        nowPlayingMovie = movies,
        topRatedMovie = movies,
        nowUpcomingMovie = movies,
        nowPopularMovie = movies
    )
}