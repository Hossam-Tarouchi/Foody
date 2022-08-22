package com.example.foodrecipes2.presentation.screens.detail_screen

import com.example.foodrecipes2.domain.model.Meal

data class DetailState (
    val recipiesItems: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val isLiked: Boolean = false
)
