package com.berger.baseapp.features.screen.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.berger.baseapp.data.model.dto.CharacterDto
import com.berger.baseapp.domain.usecase.characters.GetCharactersUseCase
import com.berger.baseapp.domain.usecase.favorite.UpdateFavoriteUseCase
import com.berger.baseapp.domain.viewstate.IViewEvent
import com.berger.baseapp.domain.viewstate.characters.CharactersViewState
import com.berger.baseapp.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by merttoptas on 13.03.2022
 */

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : BaseViewModel<CharactersViewState, CharactersViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            val params = GetCharactersUseCase.Params(config, hashMapOf())
            val pagedFlow = getCharactersUseCase(params).cachedIn(scope = viewModelScope)
            delay(1000)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    private fun updateFavorite(dto: CharacterDto) = viewModelScope.launch {
        val params = UpdateFavoriteUseCase.Params(dto)
        call(updateFavoriteUseCase(params))
    }

    override fun createInitialState() = CharactersViewState()

    override fun onTriggerEvent(event: CharactersViewEvent) {
        viewModelScope.launch {
            when (event) {
                is CharactersViewEvent.UpdateFavorite -> updateFavorite(event.dto)
            }
        }
    }
}

sealed class CharactersViewEvent : IViewEvent {
    class UpdateFavorite(val dto: CharacterDto) : CharactersViewEvent()
}