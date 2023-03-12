package com.berger.baseapp.domain.di

import com.berger.baseapp.domain.repository.CharacterRepository
import com.berger.baseapp.domain.usecase.characters.GetCharactersFilterUseCase
import com.berger.baseapp.domain.usecase.characters.GetCharactersUseCase
import com.berger.baseapp.domain.usecase.favorite.DeleteFavoriteUseCase
import com.berger.baseapp.domain.usecase.favorite.GetFavoritesUseCase
import com.berger.baseapp.domain.usecase.favorite.UpdateFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by merttoptas on 27.03.2022
 */

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideGetCharactersUseCase(
        characterRepository: CharacterRepository
    ) = GetCharactersUseCase(characterRepository)

    @ViewModelScoped
    @Provides
    fun provideUpdateFavoriteUseCase(
        characterRepository: CharacterRepository
    ) = UpdateFavoriteUseCase(characterRepository)

    @ViewModelScoped
    @Provides
    fun provideDeleteFavoriteUseCase(
        characterRepository: CharacterRepository
    ) = DeleteFavoriteUseCase(characterRepository)

    @ViewModelScoped
    @Provides
    fun provideGetFavoritesUseCase(
        characterRepository: CharacterRepository
    ) = GetFavoritesUseCase(characterRepository)

    @ViewModelScoped
    @Provides
    fun provideGetCharactersFilterUseCase(
        characterRepository: CharacterRepository
    ) = GetCharactersFilterUseCase(characterRepository)
}