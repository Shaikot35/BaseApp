package com.berger.baseapp.domain.viewstate.episodes

import androidx.compose.runtime.Stable
import com.berger.baseapp.data.model.EpisodesResultResponse
import com.berger.baseapp.domain.viewstate.IViewState

/**
 * Created by merttoptas on 19.03.2022
 */

@Stable
data class EpisodesViewState(
    val isLoading: Boolean = false,
    val data: List<EpisodesResultResponse>? = null
) : IViewState