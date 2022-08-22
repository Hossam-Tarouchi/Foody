package com.example.foodrecipes2.domain.repository

import com.example.foodrecipes2.core.util.Resource
import com.example.foodrecipes2.domain.model.Category
import com.example.foodrecipes2.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    fun getRandomFoodRecipie(): Flow<Resource<List<Meal>>>

    fun getFoodRecipieByCategory(category: String): Flow<Resource<List<Meal>>>

    fun getFoodRecipieById(id: String): Flow<Resource<List<Meal>>>

    fun searchFoodRecipie(query: String): Flow<Resource<List<Meal>>>

    fun getLikedMeals(): Flow<Resource<List<Meal>>>

    fun switchLikedRecipie(state: Boolean, meal: Meal): Flow<Resource<Boolean>>

    fun isMealLiked(idMeal: String): Flow<Resource<Meal>>

}