package com.example.foodrecipes2.presentation.screens.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodrecipes2.core.SharedWidget.CustomBottomBar
import com.example.foodrecipes2.core.SharedWidget.CustomTopBar
import com.example.foodrecipes2.core.util.Screen
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.category_list.CategoryList
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.most_popular.MostPopular
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.most_popular.MostPopularRecipie
import com.example.foodrecipes2.presentation.screens.home_screen.widgets.trending_recipies.TrendingRecipies
import com.example.foodrecipes2.ui.theme.MainWhite

@Composable
fun HomeScreen(navController: NavController){

    val viewModel: HomeScreenViewModel = hiltViewModel()
    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainWhite,
        topBar = {CustomTopBar(navController, false)},
        bottomBar = {CustomBottomBar()}
    ) {
        Column(modifier = Modifier.padding(it)
            .verticalScroll(rememberScrollState())
            ) {

            if (state.recipiesItems.size > 0 && state.showResearchResult){
                state.recipiesItems.forEach { item ->
                    MostPopularRecipie(item, true) {
                        item.idMeal.let {
                            navController.navigate(Screen.DetailScreen.withArgs(it ?: ""))
                        }
                    }
                }
            } else {
                CategoryList(navController)
                TrendingRecipies(navController)
                MostPopular(navController, true)
            }


        }
    }
}