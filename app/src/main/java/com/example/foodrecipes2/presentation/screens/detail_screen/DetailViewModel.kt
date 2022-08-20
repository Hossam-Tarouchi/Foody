package com.example.foodrecipes2.presentation.screens.detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.use_case.GetRecipiesByCategoryUC
import com.example.foodrecipes2.domain.use_case.GetRecipiesByIdUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRecipiesByIdUC: GetRecipiesByIdUC
): ViewModel() {

    private val _state = mutableStateOf(DetailState())
    var state : State<DetailState> = _state

    private val _eventFlow = MutableSharedFlow<DetailViewEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getRecipiesById(id:String) {
        viewModelScope.launch {
            getRecipiesByIdUC(id).onEach { result ->
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
}