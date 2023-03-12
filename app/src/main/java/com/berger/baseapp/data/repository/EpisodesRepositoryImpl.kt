package com.berger.baseapp.data.repository

import com.berger.baseapp.data.model.EpisodesResponse
import com.berger.baseapp.data.remote.source.EpisodesRemoteDataSource
import com.berger.baseapp.data.remote.utils.DataState
import com.berger.baseapp.domain.repository.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by merttoptas on 19.03.2022
 */

class EpisodesRepositoryImpl @Inject constructor(private val episodesRemoteDataSource: EpisodesRemoteDataSource) :
    EpisodesRepository {

    override fun getAllEpisodes(): Flow<DataState<EpisodesResponse>> = flow {
        emitAll(episodesRemoteDataSource.getAllEpisodes())
    }

    override fun getEpisode(episodeId: Int): Flow<DataState<EpisodesResponse>> = flow {
        emitAll(episodesRemoteDataSource.getEpisode(episodeId))
    }
}