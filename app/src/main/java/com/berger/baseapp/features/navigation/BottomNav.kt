package com.berger.baseapp.features.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.berger.baseapp.R
import com.berger.baseapp.features.screen.characters.navigation.charactersNavigationRoute
import com.berger.baseapp.features.screen.episodes.navigation.episodesNavigationRoute
import com.berger.baseapp.features.screen.favorites.navigation.favoritesNavigationRoute
import com.berger.baseapp.features.screen.search.navigation.searchNavigationRoute
import com.berger.baseapp.features.screen.settings.navigation.settingsNavigationRoute

/**
 * Created by merttoptas on 9.03.2022
 */

enum class BottomNav(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int
) {
    CHARACTERS(
        charactersNavigationRoute,
        R.drawable.ic_outline_people,
        R.string.characters_screen_title,
    ),
    EPISODES(
        episodesNavigationRoute,
        R.drawable.ic_baseline_movie_creation_24,
        R.string.episodes_screen_title
    ),
    FAVORITES(
        favoritesNavigationRoute,
        R.drawable.ic_baseline_favorite_24,
        R.string.favorite_screen_title,
    ),
    SEARCH(
        searchNavigationRoute,
        R.drawable.ic_baseline_search_24,
        R.string.search_screen_title,
    ),
    SETTINGS(
        settingsNavigationRoute,
        R.drawable.ic_baseline_settings,
        R.string.settings_screen_title
    )
}