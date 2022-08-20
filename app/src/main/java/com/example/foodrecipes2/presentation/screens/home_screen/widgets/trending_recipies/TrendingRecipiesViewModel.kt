package com.example.foodrecipes2.presentation.screens.home_screen.widgets.trending_recipies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.use_case.GetRandomFoodRecipiesUC
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.category_list.CategoryViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingRecipiesViewModel @Inject constructor(
    private val getRandomFoodRecipiesUC: GetRandomFoodRecipiesUC
): ViewModel() {

    private val _state = mutableStateOf(TrendingRecipiesState())
    var state : State<TrendingRecipiesState> = _state

    private val _eventFlow = MutableSharedFlow<TrendingRecipiesViewEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            getRandomFoodRecipiesUC().onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            randomRecipiesItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            randomRecipiesItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                        //delay(4000)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            randomRecipiesItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(TrendingRecipiesViewEvent())
                    }
                }
            }.launchIn(this)
        }
    }
}