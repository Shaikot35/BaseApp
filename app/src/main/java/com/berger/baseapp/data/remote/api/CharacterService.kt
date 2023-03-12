package com.berger.baseapp.data.remote.api

import com.berger.baseapp.data.model.CharacterInfoResponse
import com.berger.baseapp.data.model.CharacterResponse
import com.berger.baseapp.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by merttoptas on 10.03.2022
 */

interface CharacterService {

    @GET(Constants.CHARACTER_LIST)
    suspend fun getAllCharacters(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<CharacterResponse>

    @GET(Constants.GET_CHARACTER)
    suspend fun getCharacter(
        @Path(Constants.PARAM_ID) characterId: Int
    ): Response<CharacterInfoResponse>

    @GET(Constants.GET_CHARACTER)
    suspend fun getCharacter(
        @Url url: String
    ): Response<CharacterInfoResponse>

    @GET(Constants.CHARACTER_FILTER)
    suspend fun getFilterCharacter(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<CharacterResponse>
}