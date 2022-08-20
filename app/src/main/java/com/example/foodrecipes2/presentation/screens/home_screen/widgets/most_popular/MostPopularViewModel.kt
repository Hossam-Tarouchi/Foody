package com.example.foodrecipes2.presentation.screens.home_screen.widgets.most_popular

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.use_case.GetRandomFoodRecipiesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPopularViewModel @Inject constructor(
    private val getRandomFoodRecipiesUC: GetRandomFoodRecipiesUC
): ViewModel() {

    private val _state = mutableStateOf(MostPopularState())
    var state : State<MostPopularState> = _state

    private val _eventFlow = MutableSharedFlow<MostPopularViewEvent>()
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
                        _eventFlow.emit(MostPopularViewEvent())
                    }
                }
            }.launchIn(this)
        }
    }
}