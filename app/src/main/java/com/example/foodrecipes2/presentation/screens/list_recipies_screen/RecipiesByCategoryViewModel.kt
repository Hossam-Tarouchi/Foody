package com.example.foodrecipes2.presentation.screens.list_recipies_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.use_case.GetRandomFoodRecipiesUC
import com.example.foodrecipes2.domain.use_case.GetRecipiesByCategoryUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipiesByCategoryViewModel @Inject constructor(
    private val getRecipiesByCategoryUC: GetRecipiesByCategoryUC
): ViewModel() {

    private val _state = mutableStateOf(RecipiesByCategoryState())
    var state : State<RecipiesByCategoryState> = _state

    private val _eventFlow = MutableSharedFlow<RecipiesByCategoryViewEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getRecipiesByCategory(category:String) {
        viewModelScope.launch {
            getRecipiesByCategoryUC(category).onEach { result ->
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
                        _eventFlow.emit(RecipiesByCategoryViewEvent())
                    }
                }
            }.launchIn(this)
        }
    }
}