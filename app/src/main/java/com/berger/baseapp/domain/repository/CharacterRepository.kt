package com.berger.baseapp.domain.repository

import com.berger.baseapp.data.model.CharacterInfoResponse
import com.berger.baseapp.data.model.CharacterResponse
import com.berger.baseapp.data.model.FavoriteEntity
import com.berger.baseapp.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by merttoptas on 12.03.2022
 */

interface CharacterRepository {
    suspend fun getAllCharacters(page: Int, options: Map<String, String>): Response<CharacterResponse>
    fun getCharacter(characterId: Int): Flow<DataState<CharacterInfoResponse>>
    fun getCharacter(url: String): Flow<DataState<CharacterInfoResponse>>
    suspend fun getFilterCharacters(page: Int, options: Map<String, String>): Response<CharacterResponse>
    suspend fun getFavoriteList(): List<FavoriteEntity>
    suspend fun getFavorite(favoriteId: Int): FavoriteEntity? = null
    suspend fun deleteFavoriteById(favoriteId: Int): Unit
    suspend fun deleteFavoriteList(): Unit
    suspend fun saveFavorite(entity: FavoriteEntity): Unit
    suspend fun saveFavoriteList(entityList: List<FavoriteEntity>): Unit
}