package com.berger.baseapp.data.remote.source.impl

import com.berger.baseapp.data.local.dao.FavoriteDao
import com.berger.baseapp.data.model.CharacterInfoResponse
import com.berger.baseapp.data.model.CharacterResponse
import com.berger.baseapp.data.model.FavoriteEntity
import com.berger.baseapp.data.remote.api.CharacterService
import com.berger.baseapp.data.remote.source.CharacterRemoteDataSource
import com.berger.baseapp.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by merttoptas on 12.03.2022
 */
class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService,
    private val dao: FavoriteDao
) :
    BaseRemoteDataSource(), CharacterRemoteDataSource {

    override suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getAllCharacters(
        page: Int,
        options: Map<String, String>
    ): Response<CharacterResponse> = characterService.getAllCharacters(page, options)

    override suspend fun getFilterCharacters(
        page: Int, options: Map<String, String>
    ): Response<CharacterResponse> = characterService.getFilterCharacter(page, options)

    override suspend fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>> =
        getResult { characterService.getCharacter(characterId = characterId) }

    override suspend fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>> =
        getResult {
            characterService.getCharacter(url)
        }


    override suspend fun getFavoriteList(): List<FavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: FavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<FavoriteEntity>) = dao.insert(entityList)

}
