package com.berger.baseapp.domain.usecase.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.berger.baseapp.data.model.dto.CharacterDto
import com.berger.baseapp.domain.base.BaseUseCase
import com.berger.baseapp.domain.base.IParams
import com.berger.baseapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 12.04.2022
 */

class GetCharactersFilterUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<GetCharactersFilterUseCase.Params, PagingData<CharacterDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { CharactersFilterPagingSource(repository, param.options) }
        ).flow
    }
}