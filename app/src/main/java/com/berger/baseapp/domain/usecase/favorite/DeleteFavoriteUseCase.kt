package com.berger.baseapp.domain.usecase.favorite

import com.berger.baseapp.domain.base.BaseUseCase
import com.berger.baseapp.domain.base.IParams
import com.berger.baseapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by merttoptas on 29.03.2022
 */

class DeleteFavoriteUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<DeleteFavoriteUseCase.Params, Unit> {

    data class Params(
        val characterId: Int?
    ) : IParams

    override suspend fun invoke(param: Params): Flow<Unit> {
        param.characterId?.let {
            repository.deleteFavoriteById(param.characterId)
        } ?: kotlin.run {
            repository.deleteFavoriteList()
        }
        return flow { emit(Unit) }
    }
}