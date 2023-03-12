package com.berger.baseapp.data.di

import androidx.compose.runtime.Stable
import com.berger.baseapp.data.remote.api.EpisodesService
import com.berger.baseapp.data.remote.source.EpisodesRemoteDataSource
import com.berger.baseapp.data.remote.source.impl.EpisodesRemoteDataSourceImpl
import com.berger.baseapp.data.repository.EpisodesRepositoryImpl
import com.berger.baseapp.domain.repository.EpisodesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * Created by merttoptas on 19.03.2022
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class EpisodesModule {
    @Provides
    fun provideEpisodesService(retrofit: Retrofit): EpisodesService =
        retrofit.create(EpisodesService::class.java)

    @Provides
    fun provideEpisodesRemoteDataSource(episodesService: EpisodesService): EpisodesRemoteDataSource =
        EpisodesRemoteDataSourceImpl(episodesService)

    @Provides
    fun provideEpisodesRepository(
        episodesRemoteDataSource: EpisodesRemoteDataSource
    ): EpisodesRepository =
        EpisodesRepositoryImpl(episodesRemoteDataSource)
}