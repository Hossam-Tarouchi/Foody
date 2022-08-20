package com.example.foodrecipes2.presentation.screens.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.use_case.GetCategoriesUC
import com.example.foodrecipes2.domain.use_case.SearchRecipiesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val searchRecipiesUC: SearchRecipiesUC
): ViewModel() {
    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    private val _eventFlow = MutableSharedFlow<HomeScreenViewEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var job : Job? = null

    fun onSearch(query: String) {
        if (query.isBlank()){
            _state.value = state.value.copy(
                showResearchResult = false,
                searchQuery = query,
                recipiesItems = emptyList()
            )
            return
        }
        _state.value = state.value.copy(
            searchQuery = query,
            isLoading = true,
            showResearchResult = true
        )
        job?.cancel()
        job = viewModelScope.launch {
            delay(500)
            searchRecipiesUC(query).onEach { result ->
                System.err.println(result)
                when(result){
                    is Resource.Success -> {
                        System.err.println("fofo " + result)
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
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            recipiesItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(HomeScreenViewEvent())
                    }
                }
            }.launchIn(this)
        }
    }
}