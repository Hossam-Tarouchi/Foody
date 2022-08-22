package com.example.foodrecipes2.presentation.screens.liked_meals_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodrecipes2.core.SharedWidget.CustomTopBar
import com.example.foodrecipes2.core.util.Screen
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.most_popular.MostPopularRecipie
import com.example.foodrecipes2.ui.theme.MainWhite

@Composable
fun LikedMealsScreen(navController: NavHostController) {

    val viewModel: LikedMealsViewModel = hiltViewModel()
    val state = viewModel.state.value

    Scaffold(topBar = { CustomTopBar(navController, true) }, backgroundColor = MainWhite,) {
        Column(Modifier.fillMaxSize().padding(10.dp)
            .verticalScroll(rememberScrollState())) {
            state.recipiesItems.forEach { item -> 
                MostPopularRecipie(recipie = item, false) {
                    item.idMeal.let {
                        navController.navigate(Screen.DetailScreen.withArgs(it ?: ""))
                    }
                }
            }
        }
    }
}