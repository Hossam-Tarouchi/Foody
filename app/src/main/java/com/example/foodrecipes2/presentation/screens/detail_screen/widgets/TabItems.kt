package com.example.foodrecipes2.presentation.screens.detail_screen.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.foodrecipes2.domain.model.Meal

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun){

    data class Ingredients(val meal: Meal) : TabItem("Ingredients", { IngredientsTab(meal = meal) })
    data class Steps(val meal: Meal) : TabItem("Steps", { StepsTab(meal = meal) })
}
