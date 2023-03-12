package com.berger.baseapp.domain.repository

import com.berger.baseapp.data.model.EpisodesResponse
import com.berger.baseapp.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by merttoptas on 19.03.2022
 */
interface EpisodesRepository {
    fun getAllEpisodes(): Flow<DataState<EpisodesResponse>>
    fun getEpisode(episodeId: Int): Flow<DataState<EpisodesResponse>>
}