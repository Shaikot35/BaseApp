package com.berger.baseapp.features.screen.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.berger.baseapp.data.model.dto.CharacterDto
import com.berger.baseapp.data.remote.utils.Constants
import com.berger.baseapp.domain.usecase.characters.GetCharactersFilterUseCase
import com.berger.baseapp.domain.usecase.favorite.UpdateFavoriteUseCase
import com.berger.baseapp.domain.viewstate.IViewEvent
import com.berger.baseapp.domain.viewstate.search.SearchViewState
import com.berger.baseapp.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by merttoptas on 9.04.2022
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCharactersFilterUseCase: GetCharactersFilterUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : BaseViewModel<SearchViewState, SearchViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    override fun onTriggerEvent(event: SearchViewEvent) {
        viewModelScope.launch {
            when (event) {
                is SearchViewEvent.NewSearchEvent -> {
                    onSearch(currentState)
                }
                is SearchViewEvent.UpdateFavorite -> {
                    updateFavorite(event.dto)
                }
            }
        }
    }

    private fun onSearch(viewState: SearchViewState) {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }

            val queryData = HashMap<String, String>()
            viewState.searchText?.let { queryData[Constants.PARAM_NAME] = it }
            viewState.status.find { c -> c.selected }?.name?.let { queryData[Constants.PARAM_STATUS] = it }
            viewState.gender.find { c -> c.selected }?.name?.let { queryData[Constants.PARAM_GENDER] = it }

            val params = GetCharactersFilterUseCase.Params(config, queryData)
            delay(1000)
            val pagedFlow = getCharactersFilterUseCase(params).cachedIn(scope = viewModelScope)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    private fun updateFavorite(dto: CharacterDto) = viewModelScope.launch {
        val params = UpdateFavoriteUseCase.Params(dto)
        call(updateFavoriteUseCase(params))
    }

    fun searchText(value: String?) {
        setState { currentState.copy(searchText = value) }
    }

    fun selectGender(value: String) {
        setState { currentState.copy(gender = currentState.gender.map { it.copy(selected = it.name == value && it.selected.not()) }) }
    }

    fun selectStatus(value: String) {
        setState { currentState.copy(status = currentState.status.map { it.copy(selected = it.name == value && it.selected.not()) }) }
    }

    override fun createInitialState() = SearchViewState()
}

sealed class SearchViewEvent : IViewEvent {
    object NewSearchEvent : SearchViewEvent()
    class UpdateFavorite(val dto: CharacterDto) : SearchViewEvent()
}
