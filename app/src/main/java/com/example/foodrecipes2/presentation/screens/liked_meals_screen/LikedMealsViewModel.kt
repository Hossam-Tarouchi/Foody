package com.example.foodrecipes2.presentation.screens.liked_meals_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.use_case.GetLikedMealsUC
import com.example.foodrecipes2.domain.use_case.GetRecipiesByCategoryUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikedMealsViewModel @Inject constructor(
    private val getLikedMealsUC: GetLikedMealsUC
): ViewModel() {

    private val _state = mutableStateOf(LikedMealsState())
    var state : State<LikedMealsState> = _state

    private val _eventFlow = MutableSharedFlow<LikedMealsViewEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            getLikedMealsUC().onEach { result ->
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
                        _eventFlow.emit(LikedMealsViewEvent())
                    }
                }
            }.launchIn(this)
        }
    }
}