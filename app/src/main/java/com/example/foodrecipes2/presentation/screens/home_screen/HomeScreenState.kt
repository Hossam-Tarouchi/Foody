package com.example.foodrecipes2.presentation.screens.home_screen

import com.example.foodrecipes2.domain.model.Meal

data class HomeScreenState (
    val recipiesItems: List<Meal> = emptyList(),
    var searchQuery: String = "",
    val isLoading: Boolean = false,
    val showResearchResult: Boolean = false
)
