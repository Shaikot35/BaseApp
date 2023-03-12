package com.berger.baseapp.domain.usecase.favorite

import com.berger.baseapp.data.model.dto.CharacterDto
import com.berger.baseapp.data.model.dto.extension.toFavoriteDtoList
import com.berger.baseapp.domain.base.BaseUseCase
import com.berger.baseapp.domain.base.IParams
import com.berger.baseapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by merttoptas on 30.03.2022
 */

class GetFavoritesUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<IParams, List<CharacterDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getFavoriteList()
        emit(favorites.toFavoriteDtoList())
    }
}