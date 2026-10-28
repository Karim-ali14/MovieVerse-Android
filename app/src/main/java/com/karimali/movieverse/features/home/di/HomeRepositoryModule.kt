package com.karimali.movieverse.features.home.di

import com.karimali.movieverse.features.home.data.dataSource.remoteDataSource.HomeApiService
import com.karimali.movieverse.features.home.data.repository.HomeRepositoryImpl
import com.karimali.movieverse.features.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        homeApiService: HomeApiService
    ) : HomeRepository = HomeRepositoryImpl(homeApiService)
}