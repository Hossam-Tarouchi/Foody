package com.example.foodrecipes2.core.util

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.foodrecipes2.presentation.screens.detail_screen.DetailScreen
import com.example.foodrecipes2.presentation.screens.home_screen.HomeScreen
import com.example.foodrecipes2.presentation.screens.liked_meals_screen.LikedMealsScreen
import com.example.foodrecipes2.presentation.screens.list_recipies_screen.ListOfRecipiesScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route ){
        composable(Screen.MainScreen.route) { HomeScreen(navController) }
        composable(Screen.LikedMealsScreen.route) { LikedMealsScreen(navController) }
        composable(route = "listOfRecipies/{category}", arguments = listOf(
            navArgument("category"){
                type = NavType.StringType
                nullable = false
            }
        )) { entry -> ListOfRecipiesScreen(entry.arguments?.getString("category"), navController) }

        composable(route = "detailScreen/{id}", arguments = listOf(
            navArgument("id"){
                type = NavType.StringType
                nullable = false
            }
        )) { entry -> DetailScreen(entry.arguments?.getString("id"), navController) }
    }
}