package com.karimali.movieverse.features.home.di

import com.karimali.movieverse.features.home.data.dataSource.remoteDataSource.HomeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRemoteModule {

    @Singleton
    @Provides
    fun provideHomeRemoteDataSource(retrofit: Retrofit) : HomeApiService =
        retrofit.create(HomeApiService::class.java)

}