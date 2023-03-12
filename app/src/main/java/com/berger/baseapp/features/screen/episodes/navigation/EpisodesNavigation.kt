@file:OptIn(ExperimentalAnimationApi::class)

package com.berger.baseapp.features.screen.episodes.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.berger.baseapp.features.screen.episodes.EpisodesScreen

/**
 * Created by mertcantoptas on 23.01.2023
 */

const val episodesNavigationRoute = "episodes_route"

fun NavController.navigateToEpisodes(
    navOptions: NavOptions? = null
) {
    this.navigate(episodesNavigationRoute, navOptions)
}

fun NavGraphBuilder.episodesScreen() {
    composable(episodesNavigationRoute) {
        EpisodesScreen(
            hiltViewModel()
        )
    }
}