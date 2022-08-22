package com.example.foodrecipes2.presentation.screens.liked_meals_screen

import com.example.foodrecipes2.domain.model.Meal

data class LikedMealsState (
    val recipiesItems: List<Meal> = emptyList(),
    val isLoading: Boolean = false
)
