package com.example.foodrecipes2.presentation.screens.detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.use_case.GetRecipiesByCategoryUC
import com.example.foodrecipes2.domain.use_case.GetRecipiesByIdUC
import com.example.foodrecipes2.domain.use_case.IsMealLikedUC
import com.example.foodrecipes2.domain.use_case.SwitchLikedRecipieUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRecipiesByIdUC: GetRecipiesByIdUC,
    private val switchLikedRecipieUC: SwitchLikedRecipieUC,
    private val isMealLikedUC: IsMealLikedUC
): ViewModel() {

    private val _state = mutableStateOf(DetailState())
    var state : State<DetailState> = _state

    private val _eventFlow = MutableSharedFlow<DetailViewEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun handle(action: DetailViewAction){
        when(action){
            is DetailViewAction.GetRecipiesById -> handleGetRecipiesById(action)
            is DetailViewAction.SwitchLikedRecipie -> handleSwitchLikedRecipie(action)
            is DetailViewAction.IsMealLiked -> handleIsMealLiked(action)
        }
    }

    private fun handleGetRecipiesById(action: DetailViewAction.GetRecipiesById) {
        viewModelScope.launch {
            getRecipiesByIdUC(action.id).onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            recipiesItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            recipiesItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                        //delay(4000)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            recipiesItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(DetailViewEvent())
                    }
                }
            }.launchIn(this)
        }
    }

    private fun handleSwitchLikedRecipie(action: DetailViewAction.SwitchLikedRecipie){
        viewModelScope.launch {
            switchLikedRecipieUC(action.state, action.meal).onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLiked = action.state,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                        //delay(4000)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(DetailViewEvent())
                    }
                }
            }.launchIn(this)
        }
    }

    private fun handleIsMealLiked(action: DetailViewAction.IsMealLiked){
        viewModelScope.launch {
            isMealLikedUC(action.id).onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLiked = result.data != null,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                        //delay(4000)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(DetailViewEvent())
                    }
                }
            }.launchIn(this)
        }
    }
}