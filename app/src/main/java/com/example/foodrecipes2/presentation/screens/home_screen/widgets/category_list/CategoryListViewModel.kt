package com.example.foodrecipes2.presentation.screens.home_screen.widgets.category_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.use_case.GetCategoriesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoryUC: GetCategoriesUC
): ViewModel() {
    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

    private val _eventFlow = MutableSharedFlow<CategoryViewEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            getCategoryUC().onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            categoryItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            categoryItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                        delay(4000)
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            categoryItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(CategoryViewEvent())
                    }
                }
            }.launchIn(this)
        }
    }
}