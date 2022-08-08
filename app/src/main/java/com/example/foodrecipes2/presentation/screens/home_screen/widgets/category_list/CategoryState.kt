package com.example.foodrecipes2.presentation.screens.home_screen.widgets.category_list

import com.example.foodrecipes2.domain.model.Category

data class CategoryState (
    val categoryItems: List<Category> = emptyList(),
    val isLoading: Boolean = false
)
