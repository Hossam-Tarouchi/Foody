package com.example.foodrecipes2.presentation.screens.home_screen.widgets.trending_recipies

import com.example.foodrecipes2.domain.model.Meal

data class TrendingRecipiesState (
    val randomRecipiesItems: List<Meal> = emptyList(),
    val isLoading: Boolean = false
)
