package com.berger.baseapp.data.remote.source

import com.berger.baseapp.data.model.EpisodesResponse
import com.berger.baseapp.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 19.03.2022
 */

interface EpisodesRemoteDataSource {
    suspend fun getAllEpisodes(): Flow<DataState<EpisodesResponse>>
    suspend fun getEpisode(episodeId: Int): Flow<DataState<EpisodesResponse>>
}