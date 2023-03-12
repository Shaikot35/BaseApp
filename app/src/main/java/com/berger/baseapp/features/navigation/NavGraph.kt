package com.berger.baseapp.features.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.berger.baseapp.features.component.RickAndMortyBottomAppBar
import com.berger.baseapp.features.component.RickAndMortyFloatingActionBar
import com.berger.baseapp.features.component.RickAndMortyScaffold
import com.berger.baseapp.features.screen.characters.navigation.charactersNavigationRoute
import com.berger.baseapp.features.screen.characters.navigation.charactersScreen
import com.berger.baseapp.features.screen.charactersdetail.navigation.charactersDetailScreen
import com.berger.baseapp.features.screen.charactersdetail.navigation.navigateCharactersDetail
import com.berger.baseapp.features.screen.episodes.navigation.episodesScreen
import com.berger.baseapp.features.screen.favorites.navigation.favoritesScreen
import com.berger.baseapp.features.screen.search.navigation.searchScreen
import com.berger.baseapp.features.screen.settings.navigation.settingsScreen
import com.berger.baseapp.utils.Utility.toJson

/**
 * Created by merttoptas on 9.03.2022
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph() {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination

    RickAndMortyScaffold(
        bottomBar = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    RickAndMortyBottomAppBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            }
        },
        floatingActionButton = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    RickAndMortyFloatingActionBar(
                        navController = navController,
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = charactersNavigationRoute,
            Modifier.padding(innerPadding)
        ) {
            charactersScreen { navController.navigateCharactersDetail(it.toJson()) }
            charactersDetailScreen { navController.navigateUp() }
            episodesScreen()
            searchScreen { navController.navigateCharactersDetail(it.toJson()) }
            settingsScreen()
            favoritesScreen { navController.navigateCharactersDetail(it.toJson()) }
        }
    }
}
