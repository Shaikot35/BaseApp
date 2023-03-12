package com.berger.baseapp.features.screen.charactersdetail

import androidx.lifecycle.SavedStateHandle
import com.berger.baseapp.data.model.Result
import com.berger.baseapp.domain.viewstate.IViewEvent
import com.berger.baseapp.domain.viewstate.charactersdetail.CharactersDetailViewState
import com.berger.baseapp.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by merttoptas on 13.03.2022
 */
@HiltViewModel
class CharactersDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CharactersDetailViewState, CharactersDetailViewEvent>() {

    init {
        savedStateHandle.get<String>("characterDetail")?.let {
            setState { currentState.copy(isLoading = false, data = Result.create(it)) }
        } ?: kotlin.run {
           setEvent(CharactersDetailViewEvent.SnackBarError("Something went wrong"))
        }
    }

    override fun createInitialState() = CharactersDetailViewState()
    override fun onTriggerEvent(event: CharactersDetailViewEvent) {}
}

sealed class CharactersDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : CharactersDetailViewEvent()
}