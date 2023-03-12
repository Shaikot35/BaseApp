package com.berger.baseapp.domain.viewstate.favorites

import androidx.compose.runtime.Stable
import com.berger.baseapp.data.model.FavoriteEntity
import com.berger.baseapp.domain.viewstate.IViewState

/**
 * Created by merttoptas on 30.03.2022
 */

@Stable
data class FavoritesViewState(
    val favoritesList: List<FavoriteEntity> = emptyList(),
    val favoriteId: Int? = null,
    val isDisplay: Boolean = false,
    val isAllDeleteFavorites: Boolean = false,
    val isLoading: Boolean = false,
) : IViewState