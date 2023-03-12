package com.berger.baseapp.domain.viewstate.charactersdetail

import androidx.compose.runtime.Stable
import com.berger.baseapp.data.model.Result
import com.berger.baseapp.domain.viewstate.IViewState

/**
 * Created by merttoptas on 13.03.2022
 */
@Stable
data class CharactersDetailViewState(
    val isLoading: Boolean = false,
    val data: Result? = null
) : IViewState