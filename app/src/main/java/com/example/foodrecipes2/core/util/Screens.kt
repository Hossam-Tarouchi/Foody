package com.example.foodrecipes2.core.util

import androidx.navigation.NavArgs

sealed class Screen(
    val route:String,
) {
    object MainScreen: Screen("mainScreen")
    object ListOfRecipies: Screen("listOfRecipies")
    object DetailScreen: Screen("detailScreen")
    object LikedMealsScreen: Screen("likedMealsScreen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
