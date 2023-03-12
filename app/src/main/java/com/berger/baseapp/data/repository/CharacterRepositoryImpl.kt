package com.berger.baseapp.data.repository

import com.berger.baseapp.data.local.dao.FavoriteDao
import com.berger.baseapp.data.model.CharacterInfoResponse
import com.berger.baseapp.data.model.CharacterResponse
import com.berger.baseapp.data.model.FavoriteEntity
import com.berger.baseapp.data.remote.source.CharacterRemoteDataSource
import com.berger.baseapp.data.remote.utils.DataState
import com.berger.baseapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by merttoptas on 12.03.2022
 */

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val dao: FavoriteDao
) : CharacterRepository {

    override suspend fun getAllCharacters(
        page: Int,
        options: Map<String, String>
    ): Response<CharacterResponse> =
        characterRemoteDataSource.getAllCharacters(page = page, options = options)

    override fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getCharacter(characterId = characterId))
    }

    override fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getCharacter(url = url))
    }

    override suspend fun getFilterCharacters(
        page: Int,
        options: Map<String, String>
    ): Response<CharacterResponse> = characterRemoteDataSource.getFilterCharacters(page, options)

    override suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getFavoriteList(): List<FavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: FavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<FavoriteEntity>) = dao.insert(entityList)
}