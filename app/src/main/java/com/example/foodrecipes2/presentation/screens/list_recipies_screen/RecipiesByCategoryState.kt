package com.example.foodrecipes2.presentation.screens.list_recipies_screen

import com.example.foodrecipes2.domain.model.Meal

data class RecipiesByCategoryState (
    val recipiesItems: List<Meal> = emptyList(),
    val isLoading: Boolean = false
)
