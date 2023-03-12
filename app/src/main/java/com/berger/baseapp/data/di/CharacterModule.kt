package com.berger.baseapp.data.di

import androidx.compose.runtime.Stable
import com.berger.baseapp.data.local.dao.FavoriteDao
import com.berger.baseapp.data.remote.api.CharacterService
import com.berger.baseapp.data.remote.source.CharacterRemoteDataSource
import com.berger.baseapp.data.remote.source.impl.CharacterRemoteDataSourceImpl
import com.berger.baseapp.data.repository.CharacterRepositoryImpl
import com.berger.baseapp.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * Created by merttoptas on 12.03.2022
 */
@Stable
@Module
@InstallIn(ViewModelComponent::class)
class CharacterModule {
    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    @Provides
    fun provideCharacterRemoteDataSource(
        characterService: CharacterService,
        dao: FavoriteDao
    ): CharacterRemoteDataSource =
        CharacterRemoteDataSourceImpl(characterService, dao)

    @Provides
    fun provideCharacterRepository(
        accountRemoteDataSource: CharacterRemoteDataSource,
        dao: FavoriteDao
    ): CharacterRepository =
        CharacterRepositoryImpl(accountRemoteDataSource, dao)
}