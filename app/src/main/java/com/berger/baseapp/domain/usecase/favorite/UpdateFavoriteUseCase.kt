package com.berger.baseapp.domain.usecase.favorite

import com.berger.baseapp.data.model.dto.CharacterDto
import com.berger.baseapp.data.model.dto.extension.toFavoriteEntity
import com.berger.baseapp.domain.base.BaseUseCase
import com.berger.baseapp.domain.base.IParams
import com.berger.baseapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by merttoptas on 27.03.2022
 */

class UpdateFavoriteUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<UpdateFavoriteUseCase.Params, Unit> {

    data class Params(
        val character: CharacterDto
    ) : IParams

    override suspend fun invoke(param: Params) = flow<Unit> {
        val dto = param.character
        val character = repository.getFavorite(dto.id ?: 0)
        if (character == null) {
            repository.saveFavorite(dto.toFavoriteEntity())
        } else {
            repository.deleteFavoriteById(dto.id ?: 0)
        }
        emit(Unit)
    }
}