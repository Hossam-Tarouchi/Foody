package com.example.foodrecipes2.presentation.screens.home_screen.widgets.most_popular

import com.example.foodrecipes2.domain.model.Meal

data class MostPopularState (
    val randomRecipiesItems: List<Meal> = emptyList(),
    val isLoading: Boolean = false
)
